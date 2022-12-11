package com.example.MyBookShopApp.data.model.book.review;

import com.example.MyBookShopApp.data.Book;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_review")
public class BookReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_id",columnDefinition = "INT NOT NULL")
    private Integer bookId;

    @Column(name = "user_id",columnDefinition = "INT NOT NULL")
    private Integer userId;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String text;

    @Transient
    private int likesCount;

    @Transient
    private int dislikesCount;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Book bookReview;

    public BookReviewEntity(Integer bookId, String text) {
        this.bookId = bookId;
        this.time = LocalDateTime.now();
        this.text = text;
        this.userId = randomUserId();
    }

    public BookReviewEntity() {
    }

    private Integer randomUserId() {
        Integer result = (int) (Math.random() * 10);
        return result == 0 ? 1 : result;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Book getBookReview() {
        return bookReview;
    }

    public void setBookReview(Book bookReview) {
        this.bookReview = bookReview;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }
}
