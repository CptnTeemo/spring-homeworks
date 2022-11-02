package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private ApplicationContext contex;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> retreiveAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books",
                (ResultSet rs, int rowNum) -> {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setAuthor(rs.getString("author"));
                    book.setTitle(rs.getString("title"));
                    book.setSize(rs.getInt("size"));
                    return book;
                });
        return new ArrayList<>(books);
    }

    @Override
    public void store(Book book) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("author", book.getAuthor());
        parameterSource.addValue("title", book.getTitle());
        parameterSource.addValue("size", book.getSize());
        jdbcTemplate.update("INSERT INTO books(author, title, size) VALUES(:author, :title, :size)",
                parameterSource);
        logger.info("store new book: " + book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", bookIdToRemove);
        jdbcTemplate.update("DELETE FROM books WHERE id = :id", parameterSource);
        logger.info("remove book by id completed");
        return true;
    }

    @Override
    public boolean removeItemByRegex(String queryRegex) {
        if (isNumber(queryRegex)) {
            return removeBySize(queryRegex);
        } else {
            return removeByAuthor(queryRegex);
        }
    }

    private boolean removeBySize(String queryRegex) {
        int size = Integer.parseInt(queryRegex);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("size", size);
        jdbcTemplate.update("DELETE FROM books WHERE size = :size", parameterSource);
        logger.info("remove book by size completed");
        return true;
    }

    private boolean removeByAuthor(String queryRegex) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        int deletionResponseAuthor = 0;
        parameterSource.addValue("author", queryRegex);
        deletionResponseAuthor = jdbcTemplate.update("DELETE FROM books WHERE author = :author", parameterSource);
        if (deletionResponseAuthor > 0) {
            logger.info("remove book by author complete");
            return true;
        } else {
            return removeByTitle(queryRegex);
        }
    }

    private boolean removeByTitle(String queryRegex) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        int deletionResponseTitle = 0;
        parameterSource.addValue("title", queryRegex);
        deletionResponseTitle = jdbcTemplate.update("DELETE FROM books WHERE title = :title", parameterSource);
        logger.info(deletionResponseTitle > 0 ? "remove book by title complete" : "remove book by title failed");
        return deletionResponseTitle > 0;
    }

    public boolean isNumber(String queryRegex) {
        return queryRegex.matches("[0-9]+");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.contex = applicationContext;
    }

    private void defaultInit() {
        logger.info("default INIT in book repo bean");
    }

    private void defaultDestroy() {
        logger.info("default DESTROY in book repo bean");
    }
}
