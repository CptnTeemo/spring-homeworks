package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.model.book.file.BookFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookFileRepository extends JpaRepository<BookFileEntity, Integer> {

    public BookFileEntity findBookFileEntityByHash(String hash);
}
