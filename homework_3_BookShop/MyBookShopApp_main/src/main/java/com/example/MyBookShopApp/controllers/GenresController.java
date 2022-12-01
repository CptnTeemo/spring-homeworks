package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.dto.BooksPageDto;
import com.example.MyBookShopApp.data.dto.GenresPageDto;
import com.example.MyBookShopApp.data.dto.SearchWordDto;
import com.example.MyBookShopApp.data.dto.TagsPageDto;
import com.example.MyBookShopApp.data.model.genre.GenreEntity;
import com.example.MyBookShopApp.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/genres")
public class GenresController {

    private final GenreService genreService;

    @Autowired
    public GenresController(GenreService genreService) {
        this.genreService = genreService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("searchResults")
    public List<GenreEntity> getGenres() {
        return new ArrayList<>();
    }

//    @GetMapping
//    public String genresPage(){
//        return "genres/index";
//    }


    @GetMapping("/genres")
    public String getAllGenres(Model model) {
        List<GenreEntity> genres = genreService.getGenresList();
        Map<GenreEntity, Integer> genresMap = genreService.getCountBooksByGenre(genres);
        model.addAttribute("genres", genresMap);
//        model.addAttribute("countBooks", countBooks);
        return "genres/index";
    }

    @GetMapping(value = "/genres/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String allBooksByTag(@PathVariable("id") int id, Model model) {
        model.addAttribute("genreName", genreService.getGenreName(id));
        model.addAttribute("booksByGenre", genreService.getBooksPageByGenre(id, 0, 5));
        return "genres/slug";
    }

    @GetMapping(value = "/books/genre/{id}")
    @ResponseBody
    public BooksPageDto getBooksPage(@PathVariable("id") int id,
                                     @RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit) {
        return new BooksPageDto(genreService.getBooksPageByGenre(id, offset, limit));
    }
}
