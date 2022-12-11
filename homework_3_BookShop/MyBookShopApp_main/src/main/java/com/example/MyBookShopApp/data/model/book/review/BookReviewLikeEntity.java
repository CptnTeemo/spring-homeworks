package com.example.MyBookShopApp.data.model.book.review;

import com.example.MyBookShopApp.data.model.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_review_like")
public class BookReviewLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "review_id",columnDefinition = "INT NOT NULL")
    private Integer reviewId;

    @Column(name = "user_id", columnDefinition = "INT NOT NULL")
    private Integer userId;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private short value;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    public BookReviewLikeEntity() {
    }

    public BookReviewLikeEntity(Integer reviewId, short value) {
        this.reviewId = reviewId;
        this.userId = randomUserId();
        this.time = LocalDateTime.now();
        this.value = value;
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

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
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

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
