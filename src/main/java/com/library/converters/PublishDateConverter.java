package com.library.converters;

import com.library.utils.DateFormatter;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class PublishDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String publishYear) {
        return DateFormatter.parseDate(publishYear);
    }

}
