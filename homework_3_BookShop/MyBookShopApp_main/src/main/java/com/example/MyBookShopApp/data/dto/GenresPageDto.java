package com.example.MyBookShopApp.data.dto;

import com.example.MyBookShopApp.data.model.book.TagEntity;
import com.example.MyBookShopApp.data.model.genre.GenreEntity;

import java.util.List;

public class GenresPageDto {

    private Integer count;
    private List<GenreEntity> genres;

    public GenresPageDto(List<GenreEntity> genres) {
        this.genres = genres;
        this.count = genres.size();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }
}
