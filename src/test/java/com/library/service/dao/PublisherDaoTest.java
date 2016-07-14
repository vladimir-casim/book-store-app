package com.library.service.dao;

import com.library.DomainAwareBase;
import com.library.domain.Publisher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class PublisherDaoTest  extends DomainAwareBase {

    @Autowired
    private PublisherDao publisherDao;

    @Test
    public void testAdd(){
        int size = publisherDao.list().size();
        publisherDao.add(new Publisher("Ecsmo"));
        assertTrue (size < publisherDao.list().size());
    }

    @Test
    public void testUpdate(){
        Publisher publisher = new Publisher("Ecsmo");
        publisherDao.add(publisher);
        publisher.setName("Dummie");
        publisherDao.update(publisher);

        Publisher foundPublisher = publisherDao.find(publisher.getId());
        assertEquals("Dummie", foundPublisher.getName());
    }

    @Test
    public void testFind(){
        Publisher publisher = new Publisher("O'Reilly");
        publisherDao.add(publisher);

        Publisher foundPublisher = publisherDao.find(publisher.getId());
        assertEquals(publisher, foundPublisher);
    }

    @Test
    public void testList(){
        assertEquals(0, publisherDao.list().size());
        List<Publisher> publishers = Arrays.asList(
                new Publisher("publisher1"),
                new Publisher("publisher2"),
                new Publisher("publisher3"));
        for(Publisher publisher : publishers){
            publisherDao.add(publisher);
        }

        List<Publisher> foundList = publisherDao.list();
        assertEquals(3, foundList.size());
        for(Publisher publisher : foundList){
            assertTrue(publishers.contains(publisher));
        }
    }

    @Test
    public void testRemove(){
        Publisher publisher = new Publisher("O'Reilly");
        publisherDao.add(publisher);

        // successfully added
        assertEquals(publisher, publisherDao.find(publisher.getId()));

        // try to remove
        publisherDao.remove(publisher);
        assertNull(publisherDao.find(publisher.getId()));
    }
}
