package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.model.book.BookLikeEntity;
import com.example.MyBookShopApp.data.model.book.review.BookReviewEntity;
import com.example.MyBookShopApp.errs.BookstoreApiWrongParameterException;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    List<Book> getBooksData();

    List<Book> getBooksByAuthor(String authorName);

    List<Book> getBooksByAuthorFullName(String firstName, String lastName);

    List<Book> getBooksByTitle(String title) throws BookstoreApiWrongParameterException;

    List<Book> getBooksWithPriceBetween(Integer min, Integer max);

    List<Book> getBooksWithPrice(Integer price);

    List<Book> getBooksWithMaxDiscount();

    List<Book> getBestsellers();

    Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit);

    Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit);

    Page<Book> getPageOfRecentListBooks(LocalDate from, LocalDate to, Integer offset, Integer limit);

    List<Book> getBooksPageByAuthorFullName(String lastName, String firstName, Integer offset, Integer limit);

    List<Book> getBooksBySlugIn(String[] cookiesSlug);

    Double getBookRating(String slug);

    List<BookReviewEntity> getBookReviews(String slug);

    Book getBookBySlug(String slug);

    void saveUpdatedBook(Book bookToUpdate);

}
