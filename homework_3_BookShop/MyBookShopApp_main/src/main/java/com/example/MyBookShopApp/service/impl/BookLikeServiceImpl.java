package com.example.MyBookShopApp.service.impl;

import com.example.MyBookShopApp.data.model.book.BookLikeEntity;
import com.example.MyBookShopApp.repository.BookLikeRepository;
import com.example.MyBookShopApp.service.BookLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookLikeServiceImpl implements BookLikeService {

    private final BookLikeRepository bookLikeRepository;

    @Autowired
    public BookLikeServiceImpl(BookLikeRepository bookLikeRepository) {
        this.bookLikeRepository = bookLikeRepository;
    }

    @Override
    public void saveBookLike(BookLikeEntity bookLike) {
        bookLikeRepository.save(bookLike);
    }
}
