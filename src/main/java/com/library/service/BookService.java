package com.library.service;

import com.library.domain.Book;
import com.library.domain.Genre;

import java.util.List;

public interface BookService {
    public List<Book> findByGenre(Long key);
    /**
     * @return finds all books by Genre
     * Returns (@code null) when books list is empty
     */
    public List<Book> findByGenre(Long key, Integer pageNumber, int pageSize);
    /**
     * @return finds all books by specified title
     * Returns (@code null) when books list is empty
     */
    List<Book> findByTitle(String title);
    /**
     * @return finds all books for specified author
     * Returns (@code null) when books list is empty
     */
    List<Book> findByAuthor(String author);
    /**
     * @return finds genre for a book
     * Returns (@code null) when books list is empty
     */

    public List<Book> getBooksList(Integer pageNumber, int pageSize);

}
