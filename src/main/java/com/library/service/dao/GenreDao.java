package com.library.service.dao;

import com.library.domain.Genre;

public interface GenreDao extends GenericDao<Genre, Long> {
    boolean removeGenre(Genre genre);
}
