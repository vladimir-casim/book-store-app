package com.library.validators;

import com.library.domain.Author;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AuthorFormValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Author.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Author author = (Author) target;
        if(author.getName().length() == 0){
            errors.rejectValue("name", "author.name", "The author is empty");
        }
    }
}
