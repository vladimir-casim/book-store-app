package com.library.service.dao;

import com.library.domain.Publisher;

public interface PublisherDao extends GenericDao<Publisher, Long> {
    boolean removePublisher(Publisher publisher);
}

