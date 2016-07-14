package com.library.service.impl;

import com.library.domain.Genre;
import com.library.service.dao.GenreDao;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("genreDao")
public class GenreDaoImpl extends HibernateDao<Genre, Long> implements GenreDao {
    @Override
    public boolean removeGenre(Genre genre) {
        Query genreQuery = currentSession().createQuery(
                "from Book b where b.genre.id = :id"
        );
        genreQuery.setParameter("id", genre.getId());

        if (!genreQuery.list().isEmpty()){
            return false;
        }

        remove(genre);
        return true;
    }
}
