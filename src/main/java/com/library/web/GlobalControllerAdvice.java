package com.library.web;

import com.library.domain.Genre;
import com.library.service.dao.GenreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class GlobalControllerAdvice {

    private GenreDao genreDao;

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public GenreDao getGenreDao() {
        return genreDao;
    }

    @ModelAttribute("genres")
    public List<Genre> getGenres(){
        return genreDao.list();
    }
}

