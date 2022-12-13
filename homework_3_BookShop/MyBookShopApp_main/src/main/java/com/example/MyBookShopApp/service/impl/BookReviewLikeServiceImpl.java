package com.example.MyBookShopApp.service.impl;

import com.example.MyBookShopApp.data.model.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.repository.BookReviewLikeRepository;
import com.example.MyBookShopApp.service.BookReviewLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookReviewLikeServiceImpl implements BookReviewLikeService {

    private final BookReviewLikeRepository bookReviewLikeRepository;

    @Autowired
    public BookReviewLikeServiceImpl(BookReviewLikeRepository bookReviewLikeRepository) {
        this.bookReviewLikeRepository = bookReviewLikeRepository;
    }

    @Override
    public Integer getLikesCount(Integer id) {
        return bookReviewLikeRepository.getReviewLikesByReviewId(id);
    }

    @Override
    public Integer getDislikesCount(Integer id) {
        return bookReviewLikeRepository.getReviewDislikesByReviewId(id);
    }

    @Override
    public void saveBookReviewLike(BookReviewLikeEntity bookReviewLike) {
        bookReviewLikeRepository.save(bookReviewLike);
    }

}
