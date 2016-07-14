package com.library.service.dao;

import com.library.DateFormatter;
import com.library.DomainAwareBase;
import com.library.domain.Author;
import com.library.domain.Book;
import com.library.domain.Genre;
import com.library.domain.Publisher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class BookDaoTest  extends DomainAwareBase {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private PublisherDao publisherDao;

    private Author author;
    private Genre genre;
    private Publisher publisher;

    @Override
    public void deleteAllDomainEntities() {
        super.deleteAllDomainEntities();
        setUp();
    }

    public void setUp() {


        genre = new Genre("Fiction");
        genreDao.add(genre);

        author = new Author("Claim","Cisonger");
        authorDao.add(author);

        publisher = new Publisher("Ecsmo");
        publisherDao.add(publisher);
    }

    @Test
    public void testAdd() {
        int size = bookDao.list().size();
        Book book = mewBook();
        bookDao.add(book);

        assertTrue(size < bookDao.list().size());
    }

    @Test
    public void testUpdate() {
        Book book = mewBook();
        bookDao.add(book);
        book.setTitle("newTitle");
        bookDao.update(book);
        Book foundBook = bookDao.find(book.getId());
        assertEquals(book.getTitle(), foundBook.getTitle());
    }

    @Test
    public void testFind(){
        Book book = mewBook();
        bookDao.add(book);
        Book foundBook = bookDao.find(book.getId());

        assertEquals(book, foundBook);
    }

    @Test
    public void testList(){
        assertEquals(3, bookDao.list().size());
    }


    private Book mewBook(){
        Book book = new Book(genre, author, publisher, "book_1", "descr", null, 132, 23, getDate() , 342.0, 23.0, "Eng");
        return book;
    }

    private Date getDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1820, 06, 06);
        return DateFormatter.parseDate(DateFormatter.format(calendar.getTime()));
    }

}
