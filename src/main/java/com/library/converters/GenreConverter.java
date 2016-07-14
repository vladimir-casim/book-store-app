package com.library.converters;

import com.library.domain.Genre;
import com.library.service.dao.GenreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class GenreConverter implements Converter<String, Long> {

    private GenreDao genreDao;

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Long convert(String id) {
        //Genre genre = genreDao.find(new Long(id));
        return new Long(id);
    }
}
