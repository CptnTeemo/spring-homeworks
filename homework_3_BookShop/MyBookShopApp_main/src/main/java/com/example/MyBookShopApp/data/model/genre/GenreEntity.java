package com.example.MyBookShopApp.data.model.genre;

import com.example.MyBookShopApp.data.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
public class GenreEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "INT")
    private Integer parentId;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @ManyToMany(mappedBy = "genreEntityList")
    @JsonIgnore
    private List<Book> bookEntityList;

//    @ManyToMany(mappedBy = "genres")
//    private List<BookEntity> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookEntityList() {
        return bookEntityList;
    }

    public void setBookEntityList(List<Book> bookEntityList) {
        this.bookEntityList = bookEntityList;
    }

//    public List<BookEntity> getBooks() {
//        return books;
//    }
//
//    public void setBooks(List<BookEntity> books) {
//        this.books = books;
//    }
}
