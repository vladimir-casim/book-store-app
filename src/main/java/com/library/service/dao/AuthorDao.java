package com.library.service.dao;

import com.library.domain.Author;

public interface AuthorDao extends GenericDao<Author, Long> {
    /**
     * Tries to remove author from the system
     */
    boolean removeAuthor(Author author);
}
