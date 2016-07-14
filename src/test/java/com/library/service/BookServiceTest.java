package com.library.service;

import com.library.domain.Book;
import com.library.service.dao.BookDao;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class BookServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BookDao bookDao;

    @Autowired
    BookService bookService;

    @Test
    public void findByGenreTest(){
        assertEquals(2, bookService.findByGenre(1L));
    }
}
