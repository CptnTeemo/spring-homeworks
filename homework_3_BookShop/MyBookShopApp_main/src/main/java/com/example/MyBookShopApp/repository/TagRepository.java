package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.model.book.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

}
