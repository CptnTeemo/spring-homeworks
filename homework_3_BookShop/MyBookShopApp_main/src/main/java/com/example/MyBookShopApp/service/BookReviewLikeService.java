package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.model.book.review.BookReviewLikeEntity;

public interface BookReviewLikeService {

    Integer getLikesCount(Integer id);
    Integer getDislikesCount(Integer id);

    void saveBookReviewLike(BookReviewLikeEntity bookReviewLike);
}
