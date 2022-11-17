package com.example.MyBookShopApp.data.model.user;

import com.example.MyBookShopApp.data.model.book.BookEntity;
import com.example.MyBookShopApp.data.model.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.data.model.book.review.MessageEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime regTime;

    @Column(columnDefinition = "INT NOT NULL")
    private int balance;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @ManyToMany(mappedBy = "userEntityList")
    private List<BookEntity> bookEntityList;

    @ManyToMany(mappedBy = "userEntityListByFile")
    private List<BookEntity> bookEntityListByFile;

    @ManyToMany(mappedBy = "userEntityListByBalanceTransaction")
    private List<BookEntity> bookEntityListByBalanceTransaction;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserContactEntity userContact;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private MessageEntity message;

    @ManyToMany(mappedBy = "userListReview")
    private List<BookEntity> booksListReview;

    @OneToMany(mappedBy = "user")
    private List<BookReviewLikeEntity> bookReviewLikeEntityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookEntity> getBookEntityList() {
        return bookEntityList;
    }

    public void setBookEntityList(List<BookEntity> bookEntityList) {
        this.bookEntityList = bookEntityList;
    }

    public List<BookEntity> getBookEntityListByFile() {
        return bookEntityListByFile;
    }

    public void setBookEntityListByFile(List<BookEntity> bookEntityListByFile) {
        this.bookEntityListByFile = bookEntityListByFile;
    }

    public List<BookEntity> getBookEntityListByBalanceTransaction() {
        return bookEntityListByBalanceTransaction;
    }

    public void setBookEntityListByBalanceTransaction(List<BookEntity> bookEntityListByBalanceTransaction) {
        this.bookEntityListByBalanceTransaction = bookEntityListByBalanceTransaction;
    }

    public UserContactEntity getUserContact() {
        return userContact;
    }

    public void setUserContact(UserContactEntity userContact) {
        this.userContact = userContact;
    }

    public MessageEntity getMessage() {
        return message;
    }

    public void setMessage(MessageEntity message) {
        this.message = message;
    }

    public List<BookEntity> getBooksListReview() {
        return booksListReview;
    }

    public void setBooksListReview(List<BookEntity> booksListReview) {
        this.booksListReview = booksListReview;
    }

    public List<BookReviewLikeEntity> getBookReviewLikeEntityList() {
        return bookReviewLikeEntityList;
    }

    public void setBookReviewLikeEntityList(List<BookReviewLikeEntity> bookReviewLikeEntityList) {
        this.bookReviewLikeEntityList = bookReviewLikeEntityList;
    }
}
