package com.library.service.dao;

import com.library.DomainAwareBase;
import com.library.domain.Genre;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class GenreDaoTest extends DomainAwareBase {

    @Autowired
    private GenreDao genreDao;

    @Test
    public void testAdd(){
        int size = genreDao.list().size();
        genreDao.add(new Genre("Fiction"));
        assertTrue (size < genreDao.list().size());
    }

    @Test
    public void testUpdate(){
        Genre genre = new Genre("Fiction");
        genreDao.add(genre);
        genre.setName("Dummie");
        genreDao.update(genre);

        Genre foundGenre = genreDao.find(genre.getId());
        assertEquals("Dummie", foundGenre.getName());
    }

    @Test
    public void testFind(){
        Genre genre = new Genre("Detectiv");
        genreDao.add(genre);

        Genre foundGenre = genreDao.find(genre.getId());
        assertEquals(genre, foundGenre);
    }

    @Test
    public void testList(){
        assertEquals(2, genreDao.list().size());
//        List<Genre> genres = Arrays.asList(
//                new Genre("genre1"),
//                new Genre("genre2"),
//                new Genre("genre3"));
//        for(Genre genre : genres){
//            genreDao.add(genre);
//        }
//
//        List<Genre> foundList = genreDao.list();
//        assertEquals(3, foundList.size());
//        for(Genre genre : foundList){
//            assertTrue(genres.contains(genre));
//        }
    }

    @Test
    public void testRemove(){
        Genre genre = new Genre("Fiction");
        genreDao.add(genre);

        // successfully added
        assertEquals(genre, genreDao.find(genre.getId()));

        // try to remove
        genreDao.remove(genre);
        assertNull(genreDao.find(genre.getId()));
    }
    @Test
    public void testGenresNames(){
        List<String> genreOptions = new LinkedList<>();
        List<Genre> genres =  genreDao.list();
        for(Genre genre : genres){
            genreOptions.add(genre.getName());
        }
        assertEquals(2, genreOptions.size());
    }
}
