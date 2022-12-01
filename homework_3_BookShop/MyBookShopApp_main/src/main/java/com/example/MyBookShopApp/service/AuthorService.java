package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.Author;

import java.util.List;
import java.util.Map;

public interface AuthorService {

    Map<String, List<Author>> getAuthorsMap();

    Author getAuthorData(String lastName, String firstName);
}
