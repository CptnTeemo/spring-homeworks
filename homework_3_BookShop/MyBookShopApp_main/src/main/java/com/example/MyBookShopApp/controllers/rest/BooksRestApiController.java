package com.example.MyBookShopApp.controllers.rest;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.dto.DateForRecentBookDto;
import com.example.MyBookShopApp.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api("book data api")
public class BooksRestApiController {

    private final BookService bookService;

    @Autowired
    public BooksRestApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/by-author")
    @ApiOperation("operation to get book list of bookshop by passed author first name")
    public ResponseEntity<List<Book>> booksByAuthor(@RequestParam("author") String authorName) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(authorName));
    }

    @GetMapping("/books/by-title")
    @ApiOperation("get books by book title")
    public ResponseEntity<List<Book>> booksByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(bookService.getBooksByTitle(title));
    }

    @GetMapping("/books/by-price-range")
    @ApiOperation("get books by price range from min price to max price")
    public ResponseEntity<List<Book>> priceRangeBooks(@RequestParam("min") Integer min,
                                                      @RequestParam("max") Integer max) {
        return ResponseEntity.ok(bookService.getBooksWithPriceBetween(min, max));
    }

    @GetMapping("/books/with-max-discount")
    @ApiOperation("get books with max discount")
    public ResponseEntity<List<Book>> maxPriceBooks() {
        return ResponseEntity.ok(bookService.getBooksWithMaxDiscount());
    }

    @GetMapping("/books/bestsellers")
    @ApiOperation("get bestsellers books (which is_bestseller = 1)")
    public ResponseEntity<List<Book>> bestSellerBooks() {
        return ResponseEntity.ok(bookService.getBestsellers());
    }

    @GetMapping("/books/recent")
    @ApiOperation("get a list of books in the date range")
    public ResponseEntity<Page<Book>> recentBooks(@RequestParam("from") @DateTimeFormat(pattern= "dd.MM.yyyy") LocalDate from,
                                                  @RequestParam("to") @DateTimeFormat(pattern= "dd.MM.yyyy") LocalDate to,
                                                  @RequestParam("offset") Integer offset,
                                                  @RequestParam("limit") Integer limit) {
        DateForRecentBookDto dateForRecentBookDto = new DateForRecentBookDto(from, to);
        return ResponseEntity.ok(bookService.getPageOfRecentListBooks(dateForRecentBookDto.getFrom(),
                dateForRecentBookDto.getTo(), offset, limit));
    }

    @GetMapping("/books/recent/all")
    @ApiOperation("get books with max discount")
    public ResponseEntity<Page<Book>> getSomeBooksByPubDate(@RequestParam("from") @DateTimeFormat(pattern= "dd.MM.yyyy") LocalDate from,
                                                            @RequestParam("to") @DateTimeFormat(pattern= "dd.MM.yyyy") LocalDate to) {
        DateForRecentBookDto dateForRecentBookDto = new DateForRecentBookDto(from, to);
        return ResponseEntity.ok(bookService.getPageOfRecentListBooks(dateForRecentBookDto.getFrom(),
                dateForRecentBookDto.getTo(), 0, 200));
    }
}
