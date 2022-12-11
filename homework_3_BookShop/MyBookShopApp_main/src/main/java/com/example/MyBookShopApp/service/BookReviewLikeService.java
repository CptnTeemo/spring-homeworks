package com.example.MyBookShopApp.service;

public interface BookReviewLikeService {

    Integer getLikesCount(Integer id);
    Integer getDislikesCount(Integer id);

}
