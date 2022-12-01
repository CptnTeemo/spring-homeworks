package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.model.genre.GenreEntity;

import java.util.List;
import java.util.Map;

public interface GenreService {

    List<GenreEntity> getGenresList();

    List<Book> getBooksPageByGenre(int id, Integer offset, Integer limit);

    GenreEntity getGenreName(int id);

    Map<GenreEntity, Integer> getCountBooksByGenre(List<GenreEntity> genres);

    Integer getBooksCountByGenreId(int id);
}
