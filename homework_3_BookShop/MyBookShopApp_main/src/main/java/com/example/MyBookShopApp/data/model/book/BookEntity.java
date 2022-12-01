package com.example.MyBookShopApp.data.model.book;

import com.example.MyBookShopApp.data.model.genre.GenreEntity;
import com.example.MyBookShopApp.data.model.user.UserEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
    private Integer id;
    @Column(columnDefinition = "DATE NOT NULL")
    private Date pubDate;
    @Column(columnDefinition = "INT NOT NULL")
    private Integer isBestseller;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String slug;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;
    @Column(columnDefinition = "VARCHAR(255)")
    private String image;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "INT NOT NULL")
    private Integer price;
    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer discount;

    @ManyToMany(mappedBy = "bookEntityList")
    private List<AuthorEntity> authorEntityList;

//    @ManyToMany
//    @JoinTable(name = "book2genre",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "genre_id"))
//    private List<GenreEntity> genres;

    @ManyToMany
    @JoinTable(name = "book2user",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> userEntityList;

    @ManyToMany
    @JoinTable(name = "file_download",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> userEntityListByFile;

    @ManyToMany
    @JoinTable(name = "balance_transaction",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> userEntityListByBalanceTransaction;

    @ManyToMany
    @JoinTable(name = "book_review",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> userListReview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(Integer isBestseller) {
        this.isBestseller = isBestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public List<AuthorEntity> getAuthorEntityList() {
        return authorEntityList;
    }

    public void setAuthorEntityList(List<AuthorEntity> authorEntityList) {
        this.authorEntityList = authorEntityList;
    }

//    public List<GenreEntity> getGenres() {
//        return genres;
//    }
//
//    public void setGenres(List<GenreEntity> genres) {
//        this.genres = genres;
//    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    public List<UserEntity> getUserEntityListByFile() {
        return userEntityListByFile;
    }

    public void setUserEntityListByFile(List<UserEntity> userEntityListByFile) {
        this.userEntityListByFile = userEntityListByFile;
    }

    public List<UserEntity> getUserEntityListByBalanceTransaction() {
        return userEntityListByBalanceTransaction;
    }

    public void setUserEntityListByBalanceTransaction(List<UserEntity> userEntityListByBalanceTransaction) {
        this.userEntityListByBalanceTransaction = userEntityListByBalanceTransaction;
    }

    public List<UserEntity> getUserListReview() {
        return userListReview;
    }

    public void setUserListReview(List<UserEntity> userListReview) {
        this.userListReview = userListReview;
    }
}
