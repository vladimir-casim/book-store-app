package com.library.service;

import com.library.domain.Book;
import com.library.domain.Genre;

public interface GenreService {
    void increaseBooksCounter(Genre genre);
    void decreasBooksCounter(Genre genre);
    int getBooksCounter(Genre genre);
    public Genre findGenre(Book book);
}
