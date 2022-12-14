package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.model.user.BookstoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookstoreUserRepository extends JpaRepository<BookstoreUser, Integer> {
    BookstoreUser findBookstoreUserByEmail(String s);
}
