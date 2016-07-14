package com.library.service.impl;


import com.library.domain.Book;
import com.library.service.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDaoImpl extends HibernateDao<Book, Long> implements BookDao {
    @Override
    public boolean removeBook(Book book) {
        return false;
    }
}
