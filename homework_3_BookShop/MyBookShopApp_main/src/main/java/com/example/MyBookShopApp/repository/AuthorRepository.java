package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value = "SELECT * FROM authors WHERE last_name = :lastName AND first_name = :firstName", nativeQuery = true)
    Author getAuthorDataByName(@Param("lastName") String lastName, @Param("firstName") String firstName);
}
