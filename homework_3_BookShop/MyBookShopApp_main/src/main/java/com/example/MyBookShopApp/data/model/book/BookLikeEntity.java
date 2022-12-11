package com.example.MyBookShopApp.data.model.book;

import com.example.MyBookShopApp.data.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "books_likes")
public class BookLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Book book;

    public BookLikeEntity() {
    }

    public BookLikeEntity(Integer bookId, Integer rate) {
        this.bookId = bookId;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}