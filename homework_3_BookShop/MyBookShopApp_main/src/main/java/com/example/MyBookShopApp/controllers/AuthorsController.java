package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.dto.BooksPageDto;
import com.example.MyBookShopApp.data.dto.SearchWordDto;
import com.example.MyBookShopApp.service.AuthorService;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AuthorsController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorsController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("authorsMap")
    public Map<String,List<Author>> authorsMap(){
        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage(){
        return "/authors/index";
    }

    @GetMapping("/authors/{authorName}")
    public String authorBooksPage(@PathVariable("authorName") String authorName, Model model){
        String[] authorSplitName = authorName.split(" ");
        String lastName = authorSplitName[0];
        String firstName = authorSplitName[1];
        model.addAttribute("authorBooksPage", bookService.getBooksPageByAuthorFullName(lastName, firstName, 0, 5));
        model.addAttribute("authorName", authorName);
        model.addAttribute("authorData", authorService.getAuthorData(lastName, firstName));
        model.addAttribute("authorBooksCount", bookService.getBooksByAuthorFullName(lastName, firstName).size());
        return "/authors/slug";
    }

    @GetMapping("/books/author")
    public String authorMainPage() {
        return "/books/author";
    }

    @GetMapping(value = "/books/author/{authorName}")
    @ResponseBody
    public BooksPageDto getBooksPage(@PathVariable("authorName") String authorName,
                                     @RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {
        String[] authorSplitName = authorName.split(" ");
        String lastName = authorSplitName[0];
        String firstName = authorSplitName[1];
        return new BooksPageDto(bookService.getBooksPageByAuthorFullName(lastName, firstName, offset, limit));
    }

}
