package com.example.MyBookShopApp.service.impl;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.model.book.TagEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.TagRepository;
import com.example.MyBookShopApp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TagServiceImpl implements TagService {

    private Logger logger = Logger.getLogger(String.valueOf(TagServiceImpl.class));
    private final TagRepository tagRepository;
    private final BookRepository bookRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, BookRepository bookRepository) {
        this.tagRepository = tagRepository;
        this.bookRepository = bookRepository;
    }

    public List<TagEntity> getAllTags() {
        return new ArrayList<>(tagRepository.findAll());
    }

    public List<Book> getBooksByTag(Integer tagId) {
        List<TagEntity> tagsList = getAllTags();
        return new ArrayList<>(tagsList.get(tagId-1).getBooks());
    }

    @Override
    public TagEntity getTagName(int id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public List<Book> getBooksPageByTag(Integer id, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        Page<Book> books = bookRepository.findBooksByTagId(id, nextPage);
        return new ArrayList<>(books.getContent());
    }

}
