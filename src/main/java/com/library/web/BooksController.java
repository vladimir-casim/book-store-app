package com.library.web;

import com.library.domain.Author;
import com.library.domain.Book;
import com.library.domain.Genre;
import com.library.domain.Publisher;
import com.library.service.BookService;
import com.library.service.GenreService;
import com.library.service.dao.AuthorDao;
import com.library.service.dao.BookDao;
import com.library.service.dao.GenreDao;
import com.library.service.dao.PublisherDao;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import com.library.utils.DateFormatter;
import com.library.validators.AuthorFormValidation;
import com.library.validators.BookFormValidator;
import com.library.validators.GenreFormValidation;
import com.library.validators.PublisherFormValidator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final int PAGE_SIZE = 8;
    private BookDao bookDao;
    private GenreDao genreDao;
    private PublisherDao publisherDao;
    private AuthorDao authorDao;
    private BookService bookService;
    private GenreService genreService;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Autowired
    public void setPublisherDao(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @RequestMapping(method = RequestMethod.GET)
    private String showBooks(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model) {
        List<Book> booksPerPage =  bookService.getBooksList(page, PAGE_SIZE);
        int pagesCount = (int)Math.ceil(bookDao.list().size()/new Double(PAGE_SIZE));
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("books", booksPerPage);
        model.addAttribute("currentPage", page);
        return "index";
    }

    @RequestMapping(value = "{id}/genre", method = RequestMethod.GET)
    private String retrieveBooksByGenre(@PathVariable("id") long genreId,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                        Model model){
        List<Book> books = bookService.findByGenre(genreId, page, PAGE_SIZE);
        int pagesCount = (int)Math.ceil(bookService.findByGenre(genreId).size()/new Double(PAGE_SIZE));
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("books", books);
        model.addAttribute("currentGenre", genreId);
        model.addAttribute("currentPage", page);
        return "books_by_genre";
    }

    @RequestMapping(value = "{id}/moreinfo", method = RequestMethod.GET)
    private String showDeatils(@PathVariable("id") long id, Model model){
        Book book = bookDao.find(id);
        model.addAttribute("book", book);
        return "book_details";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchBook(
            @RequestParam("searchBy") String searchBy,
            @RequestParam("searchInput") String searchInput, Model model){
        List<Book> books = null;
        if(searchBy.equals("author")){
            books = bookService.findByAuthor(searchInput);
        }else {
            books = bookService.findByTitle(searchInput);
        }
        model.addAttribute("books", books);
        return "index";
    }
}
