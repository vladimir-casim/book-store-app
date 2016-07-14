package com.library.service.impl;

import com.library.domain.Author;
import com.library.service.dao.AuthorDao;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("authorDao")
public class AuthorDaoImpl extends HibernateDao<Author, Long> implements AuthorDao {

    @Override
    public boolean removeAuthor(Author author) {
        Query authorQuery = currentSession().createQuery(
                "from Book b where b.author.id = :id"
        );
        authorQuery.setParameter("id", author.getId());

        if (!authorQuery.list().isEmpty()){
            return false;
        }

        remove(author);
        return true;
    }
}
