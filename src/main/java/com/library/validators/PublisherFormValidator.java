package com.library.validators;

import com.library.domain.Publisher;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class PublisherFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Publisher.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Publisher publisher = (Publisher) target;
        if(publisher.getName().length() == 0){
            errors.rejectValue("name", "publisher.name", "The publisher empty");
        }
    }
}
