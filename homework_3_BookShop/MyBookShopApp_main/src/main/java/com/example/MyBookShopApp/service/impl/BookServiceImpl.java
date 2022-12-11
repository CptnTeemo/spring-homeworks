package com.example.MyBookShopApp.service.impl;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.model.book.BookLikeEntity;
import com.example.MyBookShopApp.data.model.book.review.BookReviewEntity;
import com.example.MyBookShopApp.errs.BookstoreApiWrongParameterException;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.service.BookReviewLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements com.example.MyBookShopApp.service.BookService {

    private final BookRepository bookRepository;
    private final BookReviewLikeService bookReviewLikeService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookReviewLikeService bookReviewLikeService) {
        this.bookRepository = bookRepository;
        this.bookReviewLikeService = bookReviewLikeService;
    }

    public List<Book> getBooksData() {
        return new ArrayList<>(bookRepository.findAll());
    }

    //NEW BOOK SERVICE METHODS

    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.findBooksByAuthorFirstNameContaining(authorName);
    }

    public List<Book> getBooksByTitle(String title) throws BookstoreApiWrongParameterException {
        if (title.equals("") || title.length() < 1) {
            throw new BookstoreApiWrongParameterException("Wrong values passed to one or more parameters");
        } else {
            List<Book> data = bookRepository.findBooksByTitleContaining(title);
            if (data.size() > 0) {
                return data;
            } else {
                throw new BookstoreApiWrongParameterException("No data found with specified parameters...");
            }
        }
    }

    public List<Book> getBooksWithPriceBetween(Integer min, Integer max) {
        return bookRepository.findBooksByPriceOldBetween(min, max);
    }

    public List<Book> getBooksWithPrice(Integer price) {
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxDiscount() {
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<Book> getBestsellers() {
        return bookRepository.getBestsellers();
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContaining(searchWord, nextPage);
    }

    public Page<Book> getPageOfRecentListBooks(LocalDate from, LocalDate to, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBooksByPubDateBetween(from, to, nextPage);
    }

    public List<Book> getBooksPageByAuthorFullName(String lastName, String firstName,
                                                   Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return new ArrayList<>(bookRepository.getPageBooksByAuthor(lastName, firstName, nextPage).getContent());
    }

    @Override
    public List<Book> getBooksBySlugIn(String[] cookiesSlug) {
        return bookRepository.findBooksBySlugIn(cookiesSlug);
    }

    @Override
    public Double getBookRating(String slug) {
        Book book = bookRepository.findBookBySlug(slug);
        List<BookLikeEntity> booksLikes = book.getBooksLikes();
        if (booksLikes.size() < 1) {
            return 0.0;
        } else {
            double sum = booksLikes.stream()
                    .mapToDouble(BookLikeEntity::getRate).sum();
            return sum / booksLikes.size();
        }
    }

    @Override
    public List<BookReviewEntity> getBookReviews(String slug) {
        Book book = bookRepository.findBookBySlug(slug);
        List<BookReviewEntity> reviews = book.getReviews();
        if (reviews.size() < 1) {
            return new ArrayList<>();
        } else {
            reviews.forEach(e -> {
                e.setLikesCount(bookReviewLikeService.getLikesCount(e.getId()));
                e.setDislikesCount(bookReviewLikeService.getDislikesCount(e.getId()));
            });
            return new ArrayList<>(reviews);
        }
    }

    public List<Book> getBooksByAuthorFullName(String lastName, String firstName) {
        return bookRepository.getAllBooksByAuthor(lastName, firstName);
    }

}
