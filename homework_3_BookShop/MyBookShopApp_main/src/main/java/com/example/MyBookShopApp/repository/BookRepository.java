package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthor_FirstName(String name);

    @Query("from Book")
    List<Book> customFindAllBooks();

    //NEW BOOK REST REPOSITORY COMMANDS

    List<Book> findBooksByAuthorFirstNameContaining(String authorFirstName);

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceOldBetween(Integer min, Integer max);

    List<Book> findBooksByPriceOldIs(Integer price);

    @Query("from Book where isBestseller=1")
    List<Book> getBestsellers();

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    Page<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);

    @Query(value = "SELECT * FROM books b WHERE b.pub_date BETWEEN :from AND :to ORDER BY pub_date DESC", nativeQuery = true)
    Page<Book> findBooksByPubDateBetween(@Param("from") LocalDate from,
                                         @Param("to") LocalDate to, Pageable nextPage);

    @Query(value = "SELECT * FROM books ORDER BY bought DESC, cart DESC", nativeQuery = true)
    Page<Book> getPopularBooks(Pageable nextPage);

    @Query(value = "SELECT * FROM books b JOIN authors a ON b.author_id=a.id WHERE a.last_name = :lastName AND a.first_name = :firstName",
            nativeQuery = true)
    List<Book> getAllBooksByAuthor(@Param("lastName") String lastName,
                                   @Param("firstName") String firstName);

    @Query(value = "SELECT * FROM books b JOIN authors a ON b.author_id=a.id WHERE a.last_name = :lastName AND a.first_name = :firstName",
            nativeQuery = true)
    Page<Book> getPageBooksByAuthor(@Param("lastName") String lastName,
                                   @Param("firstName") String firstName,
                                    Pageable nextPage);

    Page<Book> findBooksByTagId(Integer id, Pageable nextPage);

    @Query(value = "SELECT * FROM books b JOIN book2genre bg ON b.id=bg.book_id " +
            "JOIN genre g ON g.id=bg.genre_id " +
            "WHERE g.id = :id OR g.parent_id = :id",
            nativeQuery = true)
    Page<Book> getBooksPageByGenreId(@Param("id") Integer id, Pageable nextPage);

    @Query(value = "SELECT COUNT(*) FROM books b JOIN book2genre bg ON b.id=bg.book_id " +
            "JOIN genre g ON g.id=bg.genre_id " +
            "WHERE g.id = :id OR g.parent_id = :id",
            nativeQuery = true)
    Integer getBooksCountByGenreId(@Param("id") Integer id);

    Book findBookBySlug(String slug);

    List<Book> findBooksBySlugIn(String[] slugs);
}
