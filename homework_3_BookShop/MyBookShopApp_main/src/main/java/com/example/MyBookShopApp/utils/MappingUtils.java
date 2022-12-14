package com.example.MyBookShopApp.utils;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    public BookDto mapBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(bookDto.getId());
        bookDto.setPubDate(book.getPubDate());
        bookDto.setIsBestseller(bookDto.getIsBestseller());
        bookDto.setSlug(bookDto.getSlug());
        bookDto.setTitle(bookDto.getTitle());
        bookDto.setImage(bookDto.getImage());
        bookDto.setDescription(bookDto.getDescription());
        bookDto.setPriceOld(bookDto.getPriceOld());
        bookDto.setPrice(bookDto.getPrice());
        return bookDto;
    }

}
