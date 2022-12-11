package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.model.book.review.BookReviewLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewLikeRepository extends JpaRepository<BookReviewLikeEntity, Integer> {

    @Query(value = "SELECT COUNT(*) FROM book_review_like brl WHERE brl.review_id = :id AND brl.value = '1'", nativeQuery = true)
    Integer getReviewLikesByReviewId(@Param("id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM book_review_like brl WHERE brl.review_id = :id AND brl.value = '-1'", nativeQuery = true)
    Integer getReviewDislikesByReviewId(@Param("id") Integer id);
}
