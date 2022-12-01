package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.dto.BooksPageDto;
import com.example.MyBookShopApp.data.dto.SearchWordDto;
import com.example.MyBookShopApp.data.dto.TagsPageDto;
import com.example.MyBookShopApp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TagsController {

    private final TagService tagService;

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @Autowired
    public TagsController(TagService tagService) {
        this.tagService = tagService;
    }

//    @ModelAttribute("booksByTag")
//    public List<TagEntity> getTags() {
//        return tagService.getBooksByTag();
//    }

    @GetMapping("/tags")
    public String tags() {
        return "tags/index";
    }


    @GetMapping(value = "/tags/{tagId}", produces = MediaType.TEXT_HTML_VALUE)
    public String allBooksByTag(@PathVariable("tagId") int id, Model model) {
        model.addAttribute("tagName", tagService.getTagName(id));
        model.addAttribute("booksByTag", tagService.getBooksPageByTag(id, 0, 5));
        return "tags/index";
    }

    @GetMapping(value = "/books/tag/{tagId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BooksPageDto getBooksPage(@PathVariable("tagId") int id,
                                    @RequestParam("offset") Integer offset,
                                    @RequestParam("limit") Integer limit) {
        return new BooksPageDto(tagService.getBooksPageByTag(id, offset, limit));
    }
}
