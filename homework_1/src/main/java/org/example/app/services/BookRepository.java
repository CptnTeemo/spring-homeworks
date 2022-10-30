package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByAuthorName(String authorNameToRemove) {
        boolean isDeleted = false;
        for (Book book : retreiveAll()) {
            if (book.getAuthor().equals(authorNameToRemove)) {
                logger.info("remove book by author name completed: " + book);
                repo.remove(book);
                isDeleted = true;
            }
        }
        return isDeleted;
    }

    @Override
    public boolean removeItemBySize(Integer bookSizeToRemove) {
        boolean isDeleted = false;
        for (Book book : retreiveAll()) {
            if (book.getSize().equals(bookSizeToRemove)) {
                logger.info("remove book by author name completed: " + book);
                repo.remove(book);
                isDeleted = true;
            }
        }
        return isDeleted;
    }

    @Override
    public boolean removeItemByRegex(String queryRegex) {
        boolean isDeleted = false;
        if (isNumber(queryRegex)) {
            Integer size = Integer.parseInt(queryRegex);
            for (Book book : retreiveAll()) {
                if (book.getSize() == size) {
                    logger.info("remove book by regex (size) completed: " + book);
                    repo.remove(book);
                    isDeleted = true;
                }
            }
            return isDeleted;
        }
        for (Book book : retreiveAll()) {
            if (book.getAuthor().equals(queryRegex)
                    || book.getTitle().equals(queryRegex)) {
                logger.info("remove book by regex completed: " + book);
                repo.remove(book);
                isDeleted = true;
            }
        }
        return isDeleted;
    }

    public boolean isNumber(String queryRegex) {
        return queryRegex.matches("[0-9]+");
    }
}
