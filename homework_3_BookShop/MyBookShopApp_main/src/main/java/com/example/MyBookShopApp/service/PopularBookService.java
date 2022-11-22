package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.Book;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface PopularBookService {

    List<Book> getPopularBooks(Integer offset, Integer limit);

}
