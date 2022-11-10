package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final JdbcTemplate jdbcTemplate;
    private final BookService bookService;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate, BookService bookService) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookService = bookService;
    }

    public void fillingAuthorsTable() {
        List<Book> books = bookService.getBooksData();
        books.forEach(book -> {
            jdbcTemplate.update(
                    "INSERT INTO authors (id) VALUES (?)", book.getId()
            );
        });
    }

    public Map<String, List<Author>> getAuthorsMap() {
        List<Author> authors = jdbcTemplate.query("SELECT * FROM AUTHORS a JOIN BOOKS b on a.id = b.id",
                (ResultSet rs, int rowNum) -> {
                    Author author = new Author();
                    author.setAuthorName(rs.getString("author"));
                    return author;
                });
        return authors.stream()
                .collect(Collectors.groupingBy((a) -> {
                    String[] fullName = a.getAuthorName().split(" ");
                    return fullName[1].substring(0, 1);
                }));
    }
}
