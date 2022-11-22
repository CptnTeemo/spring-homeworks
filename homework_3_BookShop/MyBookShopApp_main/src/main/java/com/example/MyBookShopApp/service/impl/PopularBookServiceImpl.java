package com.example.MyBookShopApp.service.impl;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.service.PopularBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PopularBookServiceImpl implements PopularBookService {

    private final BookRepository bookService;

    @Autowired
    public PopularBookServiceImpl(BookRepository bookService) {
        this.bookService = bookService;
    }


    @Override
    public List<Book> getPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        Page<Book> popularBooks = bookService.getPopularBooks(nextPage);
        popularBooks.forEach(e -> e.setPopular(e.getBought() + e.getCart()*0.7 + e.getKept()*0.4));
        List<Book> listPopularBooks = new ArrayList<>(popularBooks.getContent());
        Collections.sort(listPopularBooks, Collections.reverseOrder());
        return new ArrayList<>(listPopularBooks);
    }
}
