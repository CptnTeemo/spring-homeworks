package com.example.MyBookShopApp.service.impl;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements com.example.MyBookShopApp.service.AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Map<String, List<Author>> getAuthorsMap() {
        List<Author> authors = authorRepository.findAll();

        return new TreeMap<>(authors.stream()
                .collect(Collectors.groupingBy((Author a) -> {return a.getLastName().substring(0,1);})));
    }

    @Override
    public Author getAuthorData(String lastName, String firstName) {
        return authorRepository.getAuthorDataByName(lastName, firstName);
    }
}
