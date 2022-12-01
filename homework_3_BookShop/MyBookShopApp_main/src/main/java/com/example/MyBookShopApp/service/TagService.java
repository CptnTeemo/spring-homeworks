package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.model.book.TagEntity;

import java.util.List;

public interface TagService {

    List<TagEntity> getAllTags();

    List<Book> getBooksByTag(Integer tagId);

    TagEntity getTagName(int id);

    List<Book> getBooksPageByTag(Integer id, Integer offset, Integer limit);
}
