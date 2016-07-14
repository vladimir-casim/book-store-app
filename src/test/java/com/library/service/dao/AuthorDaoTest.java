package com.library.service.dao;

import com.library.DomainAwareBase;
import com.library.domain.Author;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class AuthorDaoTest extends DomainAwareBase {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testAdd(){
        int size = authorDao.list().size();
        authorDao.add(new Author("vova","casim"));
        assertTrue (size < authorDao.list().size());
    }

    @Test
    public void testUpdate(){
        Author author = new Author("Claim","Cisonger");
        authorDao.add(author);
        author.setName("Baban");
        authorDao.update(author);

        Author foundAuthor = authorDao.find(author.getId());
        assertEquals("Baban", foundAuthor.getName());
    }

    @Test
    public void testFind(){
        Author author = new Author("Robert","Bloch");
        authorDao.add(author);

        Author foundAuthor = authorDao.find(author.getId());
        assertEquals(author, foundAuthor);
    }

    @Test
    public void testList(){
        assertEquals(0, authorDao.list().size());
        List<Author> authors = Arrays.asList(
                new Author("name1", "surname1"),
                new Author("name2", "surname2"),
                new Author("name3", "surname3"));
        for(Author author : authors){
            authorDao.add(author);
        }

        List<Author> foundList = authorDao.list();
        assertEquals(3, foundList.size());
        for(Author author : foundList){
            assertTrue(authors.contains(author));
        }
    }

    @Test
    public void testRemove(){
        Author author = new Author("Robert","Bloch");
        authorDao.add(author);

        // successfully added
        assertEquals(author, authorDao.find(author.getId()));

        // try to remove
        authorDao.remove(author);
        assertNull(authorDao.find(author.getId()));
    }

    @Test
    public void testRemoveAuthor(){

    }
}
