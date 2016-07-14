package com.library.web;

import com.library.domain.Author;
import com.library.domain.Book;
import com.library.domain.Genre;
import com.library.domain.Publisher;
import com.library.service.BookService;
import com.library.service.GenreService;
import com.library.service.dao.BookDao;
import com.library.service.dao.GenreDao;
import com.library.utils.DateFormatter;
import com.library.validators.AuthorFormValidation;
import com.library.validators.BookFormValidator;
import com.library.validators.GenreFormValidation;
import com.library.validators.PublisherFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books/admin")
public class AdminController {
    public final int PAGE_SIZE = 8;
    private BookDao bookDao;
    private GenreDao genreDao;
    private GenreService genreService;
    private BookService bookService;

    @Autowired
    BookFormValidator bookFormValidator;

    @InitBinder("book")
    public void initBookBinder(WebDataBinder binder){
        binder.setValidator(bookFormValidator);
    }

    @InitBinder("publisher")
    public void initPublisherBinder(WebDataBinder binder){
        binder.setValidator( new PublisherFormValidator());
    }

    @InitBinder("author")
    public void initAuthorBinder(WebDataBinder binder) { binder.setValidator( new AuthorFormValidation()); }

    @InitBinder("genreFormBinding")
    public void initGenreBinder(WebDataBinder binder) { binder.setValidator( new GenreFormValidation()); }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    private String administratorPage(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page, Model model){
        List<Book> booksPerPage =  bookService.getBooksList(page, PAGE_SIZE);
        int pagesCount = (int)Math.ceil(bookDao.list().size()/new Double(PAGE_SIZE));
        model.addAttribute("books", booksPerPage);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("currentPage", page);
        return "administrator";
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    private String delete(@PathVariable("id") long id){
        Book book = bookDao.find(id);
        genreService.decreasBooksCounter(genreService.findGenre(book));
        bookDao.remove(book);
        return "redirect:/books/admin";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    private String addBook(ModelMap model){
        Book book = new Book();
        Publisher publisher = new Publisher();
        Author author = new Author();
        Genre genre = new Genre();

        book.setPublisher(publisher);
        book.setAuthor(author);
        book.setGenre(genre);

        model.addAttribute("book", book);
        model.addAttribute("publisher", publisher);
        model.addAttribute("author", author);
        model.addAttribute("genreFormBinding", genre);

        return "add_book";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    private String processAddNewBookForm(
            @ModelAttribute("book")  Book book, BindingResult resultBook,
            @Valid @ModelAttribute("genreFormBinding") Genre genreForm, BindingResult resultGenre,
            @RequestParam("bookFile") MultipartFile bookFile,
            HttpServletRequest request
    ){


        if (!bookFile.isEmpty()) {
            try {
                byte[] bytes = bookFile.getBytes();
                // Creating the directory to store file
                HttpSession session = request.getSession();
                ServletContext sc = session.getServletContext();
                String rootPath = sc.getRealPath("/");
                System.out.println("\n rootPath " + rootPath + "\n");
                File dir = new File(rootPath + File.separator + "resources/files");
                if (!dir.exists())  dir.mkdirs();

                // Create the file on server
                String bookNameForSaving =  DateFormatter.formatDateForBookName(new Date()) + bookFile.getOriginalFilename();
                File serverFile = new File(dir.getAbsolutePath() + File.separator + bookNameForSaving);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes); stream.close();
                book.setBookFilePath(bookNameForSaving);
                System.out.println("\nServer File Location=" + serverFile.getAbsolutePath() + "\n");
                System.out.println("\nYou successfully uploaded file=" + bookFile.getOriginalFilename() + "\n");
            } catch (Exception e) {
                return "You failed to upload " + bookFile.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            System.out.println("\nYou failed to upload " + bookFile.getOriginalFilename()
                    + " because the file was empty.\n");
        }
        bookFormValidator.validate(book, resultBook);
        if(resultBook.hasErrors() || resultGenre.hasErrors()){
            return "add_book";
        }
        Genre genre = genreDao.find(new Long(genreForm.getName()));
        book.setGenre(genre);
        genreService.increaseBooksCounter(genre);
        bookDao.add(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String updatingBookPage(@PathVariable("id") Long bookId, Model model){
        Book book = bookDao.find(bookId);
        Genre genre = new Genre();
        genre.setName(String.valueOf(genreService.findGenre(book).getId()));
        model.addAttribute("book", book);
        model.addAttribute("genreFormBinding", genre);
        return "update_book";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdatingBook(@Valid @ModelAttribute("book")  Book book, BindingResult resultBook,
                             @Valid @ModelAttribute("genreFormBinding") Genre genreForm, BindingResult resultGenre,
                                      @RequestParam("imageFileForUpdating") String imageFileForUpdating,
                                      @RequestParam("bookFileForUpdating") MultipartFile bookFileForUpdating,
                                      HttpServletRequest request
                                      //@ModelAttribute("currentImage") String currentImage
    ){
        if(resultBook.hasErrors() || resultGenre.hasErrors()){
            return "update_book";
        }
        if(imageFileForUpdating != null){
            book.setImageFile(imageFileForUpdating);
        }

        if (!bookFileForUpdating.isEmpty()) {
            try {
                byte[] bytes = bookFileForUpdating.getBytes();
                // Creating the directory to store file
                HttpSession session = request.getSession();
                ServletContext sc = session.getServletContext();
                String rootPath = sc.getRealPath("/");
                System.out.println("\n rootPath " + rootPath + "\n");
                File dir = new File(rootPath + File.separator + "resources/files");
                if (!dir.exists())  dir.mkdirs();

                // Create the file on server
                String bookNameForSaving = DateFormatter.formatDateForBookName(new Date()) + bookFileForUpdating.getOriginalFilename();
                File serverFile = new File(dir.getAbsolutePath() + File.separator + bookNameForSaving);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes); stream.close();
                book.setBookFilePath(bookNameForSaving);
                System.out.println("\nServer File Location=" + serverFile.getAbsolutePath() + "\n");
                System.out.println("\nYou successfully uploaded file=" + bookFileForUpdating.getOriginalFilename() + "\n");
            } catch (Exception e) {
                return "You failed to upload " + bookFileForUpdating.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            System.out.println("\nYou failed to upload " + bookFileForUpdating.getOriginalFilename()
                    + " because the file was empty.\n");
        }

        Genre newGenre = genreDao.find(new Long(genreForm.getName()));
        Genre oldGenre = genreService.findGenre(book);
        if(newGenre.getId() != oldGenre.getId()){
            genreService.decreasBooksCounter(oldGenre);
            genreService.increaseBooksCounter(newGenre);
        }
        book.setGenre(newGenre);
        bookDao.update(book);

        return "redirect:/books/admin";
    }

    @RequestMapping(value = "/review")
    public String review(@ModelAttribute Book book){
        System.out.println("Invoking review()");
        return "book_rewiew";
    }
}
