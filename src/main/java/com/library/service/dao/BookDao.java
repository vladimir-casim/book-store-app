package com.library.service.dao;

import com.library.domain.Book;

public interface BookDao extends GenericDao<Book, Long> {
    boolean removeBook(Book book);
}
