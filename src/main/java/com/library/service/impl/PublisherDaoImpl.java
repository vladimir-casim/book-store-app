package com.library.service.impl;

import com.library.domain.Publisher;
import com.library.service.dao.PublisherDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("publisherDao")
public class PublisherDaoImpl extends HibernateDao<Publisher, Long> implements PublisherDao {
    @Override
    public boolean removePublisher(Publisher publisher) {
        Query authorQuery = currentSession().createQuery(
                "from Book b where b.publisher.id = :id"
        );
        authorQuery.setParameter("id", publisher.getId());

        if (!authorQuery.list().isEmpty()){
            return false;
        }

        remove(publisher);
        return true;
    }
}
