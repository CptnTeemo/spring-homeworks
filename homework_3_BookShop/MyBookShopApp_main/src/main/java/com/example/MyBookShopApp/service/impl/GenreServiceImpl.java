package com.example.MyBookShopApp.service.impl;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.model.genre.GenreEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.GenreRepository;
import com.example.MyBookShopApp.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    public List<GenreEntity> getGenresList() {
        return new ArrayList<>(genreRepository.findAll());
    }

    @Override
    public List<Book> getBooksPageByGenre(int id, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        List<Book> books = bookRepository.getBooksPageByGenreId(id, nextPage).getContent();
        return new ArrayList<>(books);
    }

    @Override
    public GenreEntity getGenreName(int id) {
        return genreRepository.findById(id).get();
    }

    @Override
    public Map<GenreEntity, Integer> getCountBooksByGenre(List<GenreEntity> genres) {
        Map<GenreEntity, Integer> countBooks = new LinkedHashMap<>();
        genres.forEach(e -> {
            countBooks.put(e, getBooksCountByGenreId(e.getId()));
        });
        return new LinkedHashMap<>(countBooks);
    }

    @Override
    public Integer getBooksCountByGenreId(int id) {
        return bookRepository.getBooksCountByGenreId(id);
    }

}
