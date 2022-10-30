package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);
    boolean removeItemByAuthorName(String authorNameToRemove);

    boolean removeItemBySize(Integer bookSizeToRemove);

    boolean removeItemByRegex(String queryRegex);
}
