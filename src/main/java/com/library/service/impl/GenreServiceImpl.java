package com.library.service.impl;

import com.library.domain.Book;
import com.library.domain.Genre;
import com.library.service.GenreService;
import com.library.service.dao.GenreDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
@Service("genreService")
public class GenreServiceImpl implements GenreService {

    private GenreDao genreDao;
    private SessionFactory sessionFactory;

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void increaseBooksCounter(Genre genre) {
        int booksNumber = genre.getBooksCount();
        Query bookQuery = sessionFactory.openSession().createQuery(
                "from Book b where b.genre.id = :id"
        );
        bookQuery.setParameter("id", genre.getId());
        booksNumber = bookQuery.list().size();
        genre.setBooksCount(++booksNumber);
        genreDao.update(genre);
    }

    @Override
    public void decreasBooksCounter(Genre genre) {
        int booksNumber = genre.getBooksCount();
        if(booksNumber != 0){
            genre.setBooksCount(--booksNumber);
            genreDao.update(genre);
        }
    }

    @Override
    public Genre findGenre(Book book) {
        Criteria criteria = currentSession().createCriteria(Genre.class, "genre");
        criteria.add(Restrictions.eq("genre.id", book.getGenre().getId()));
        List<Genre> genres = criteria.list();
        return genres.get(0);
    }

    @Override
    public int getBooksCounter(Genre genre) {
        return 0;
    }
}
