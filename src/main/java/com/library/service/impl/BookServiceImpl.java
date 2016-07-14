package com.library.service.impl;

import com.library.domain.Book;
import com.library.domain.Genre;
import com.library.service.BookService;
import com.library.service.dao.BookDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
@Service("bookService")
public class BookServiceImpl implements BookService {

    private SessionFactory sessionFactory;
    private BookDao bookDao;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Book> findByGenre(Long key, Integer pageNumber, int pageSize) {
        List<Book> foundBooks = null;
        Criteria criteria = currentSession().createCriteria(Book.class, "book");
        criteria.createCriteria("book.genre", "genre");
        criteria.add(Restrictions.eq("genre.id", key));
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        foundBooks = criteria.list();
        return foundBooks;
    }

    @Override
    public List<Book> findByGenre(Long key){
        Criteria criteria = currentSession().createCriteria(Book.class, "book");
        criteria.createCriteria("book.genre", "genre");
        criteria.add(Restrictions.eq("genre.id", key));
        return criteria.list();
    }

    @Override
    public List<Book> findByTitle(String inuptString) {
        Criteria criteria = currentSession().createCriteria(Book.class, "book");
        criteria.add(Restrictions.ilike("title", "%"+inuptString+"%"));
        return criteria.list();
    }

    @Override
    public List<Book> findByAuthor(String inuptString) {
        Criteria criteria = currentSession().createCriteria(Book.class, "book");
        criteria.createCriteria("book.author", "author");
        criteria.add(Restrictions.ilike("author.name", "%"+inuptString+"%"));
        return criteria.list();
    }



    @Override
    public List<Book> getBooksList(Integer pageNumber, int pageSize){
        List<Book> foundBooks = null;
        Criteria criteria = currentSession().createCriteria(Book.class, "book");
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        foundBooks = criteria.list();
        return foundBooks;
    }
}
