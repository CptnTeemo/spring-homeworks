package com.example.MyBookShopApp.service.impl;

import com.example.MyBookShopApp.data.model.user.BookstoreUser;
import com.example.MyBookShopApp.repository.BookstoreUserRepository;
import com.example.MyBookShopApp.security.BookstoreUserDetails;
import com.example.MyBookShopApp.service.BookstoreUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookstoreUserDetailsServiceImpl implements BookstoreUserDetailsService {
    private final BookstoreUserRepository bookstoreUserRepository;
    @Autowired
    public BookstoreUserDetailsServiceImpl(BookstoreUserRepository bookstoreUserRepository) {
        this.bookstoreUserRepository = bookstoreUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BookstoreUser bookstoreUser = bookstoreUserRepository.findBookstoreUserByEmail(s);
        if (bookstoreUser != null) {
            return new BookstoreUserDetails(bookstoreUser);
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }
}
