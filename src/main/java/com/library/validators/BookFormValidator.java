package com.library.validators;

import com.library.domain.Book;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.book.title");
        if(book.getTitle().length() == 0){
            errors.rejectValue("title", "book.title", "The title field is nempty");
        }
        if(book.getDescription().length() == 0){
            errors.rejectValue("description", "book.description", "The description field is nempty");
        }
        if(book.getLanguage().length() == 0){
            errors.rejectValue("language", "book.language", "The language field is nempty");
        }
        if(book.getPageCount() == 0){
            errors.rejectValue("pageCount", "book.pageCount", "The pageCount field is nempty");
        }
        if(book.getAuthor().getName().length() == 0){
            errors.rejectValue("author.name", "book.author.name", "The author field is nempty");
        }
        if(book.getPublisher().getName().length() == 0){
            errors.rejectValue("publisher.name", "book.publisher.name", "The publisher field is nempty");
        }
        if(book.getPublishYear() == null){
            errors.rejectValue("publishYear", "book.publishYear", "Incorrect publishing date");
        }
        if(book.getImageFile() == null || book.getImageFile().length() == 0){
            errors.rejectValue("imageFile", "book.imageFile", "Incorrect image Image");
        }
        if(book.getBookFilePath() == null || book.getBookFilePath().length() == 0){
            errors.rejectValue("bookFilePath", "book.bookFilePath", "Incorrect book file");
        }
    }
}
