package com.example.MyBookShopApp.service.impl;

import com.example.MyBookShopApp.data.model.book.review.BookReviewEntity;
import com.example.MyBookShopApp.repository.BookReviewRepository;
import com.example.MyBookShopApp.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookReviewServiceImpl implements BookReviewService {

    private final BookReviewRepository bookReviewRepository;

    @Autowired
    public BookReviewServiceImpl(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    @Override
    public void saveBookReview(BookReviewEntity bookReview) {
        bookReviewRepository.save(bookReview);
    }
}
