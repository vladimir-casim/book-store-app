package com.library.validators;


import com.library.domain.Book;
import com.library.domain.Genre;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class GenreFormValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Genre.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Genre genre = (Genre) target;
        if(genre.getName().length() == 0){
            errors.rejectValue("name", "genreFormBinding.name", "The genre field is nempty");
        }
    }
}
