package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.ResourceStorage;
import com.example.MyBookShopApp.data.model.book.BookLikeEntity;
import com.example.MyBookShopApp.data.model.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.model.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.service.BookLikeService;
import com.example.MyBookShopApp.service.BookReviewLikeService;
import com.example.MyBookShopApp.service.BookReviewService;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final ResourceStorage storage;
    private final BookLikeService bookLikeService;
    private final BookService bookService;
    private final BookReviewService bookReviewService;
    private final BookReviewLikeService bookReviewLikeService;

    @Autowired
    public BooksController(ResourceStorage storage,
                           BookLikeService bookLikeService,
                           BookService bookService,
                           BookReviewService bookReviewService,
                           BookReviewLikeService bookReviewLikeService) {
        this.storage = storage;
        this.bookLikeService = bookLikeService;
        this.bookService = bookService;
        this.bookReviewService = bookReviewService;
        this.bookReviewLikeService = bookReviewLikeService;
    }

    @GetMapping("/{slug}")
    public String bookPage(@PathVariable("slug") String slug, Model model) {
        Book book = bookService.getBookBySlug(slug);
        model.addAttribute("slugBook", book);
        model.addAttribute("bookRating", bookService.getBookRating(slug));
        model.addAttribute("bookReviews", bookService.getBookReviews(slug));
        return "/books/slug";
    }

    @PostMapping("/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file, @PathVariable("slug") String slug) throws IOException {
        String savePath = storage.saveNewBookImage(file, slug);
        Book bookToUpdate = bookService.getBookBySlug(slug);
        bookToUpdate.setImage(savePath);
        bookService.saveUpdatedBook(bookToUpdate); //save new path in db here
        return ("redirect:/books/" + slug);
    }

    @GetMapping("/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException {

        Path path = storage.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: " + path);

        MediaType mediaType = storage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: " + mediaType);

        byte[] data = storage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file data len: " + data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource((data)));
    }

    @GetMapping("/slugmy")
    public String bookSlugPage() {
        return "/books/slugmy";
    }

    @PostMapping("/rateBook")
    public ResponseEntity<Object> rateBook(@RequestParam("bookId") String slug,
                                           @RequestParam("value") Integer rate) {
        Book book = bookService.getBookBySlug(slug);
        BookLikeEntity bookLike = new BookLikeEntity(book.getId(), rate);
        bookLikeService.saveBookLike(bookLike);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/bookReview")
    public ResponseEntity<Object> bookReview(@RequestParam("bookId") String slug,
                                             @RequestParam("text") String text) {
        Book book = bookService.getBookBySlug(slug);
        BookReviewEntity bookReview = new BookReviewEntity(book.getId(), text);
        bookReviewService.saveBookReview(bookReview);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/rateBookReview")
    public ResponseEntity<Object> bookReviewLike(@RequestParam("value") Short value,
                                                 @RequestParam("reviewid") Integer reviewId) {
        BookReviewLikeEntity bookReviewLike = new BookReviewLikeEntity(reviewId, value);
        bookReviewLikeService.saveBookReviewLike(bookReviewLike);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
