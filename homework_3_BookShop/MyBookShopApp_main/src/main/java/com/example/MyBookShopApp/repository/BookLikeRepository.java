package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.model.book.BookLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLikeRepository extends JpaRepository<BookLikeEntity, Integer> {
}
