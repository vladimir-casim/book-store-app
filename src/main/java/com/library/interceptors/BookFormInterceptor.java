package com.library.interceptors;

import com.library.domain.Book;
import com.library.domain.Publisher;
import com.library.utils.DateFormatter;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

public class BookFormInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Book book = (Book)modelAndView.getModel().get("book");
        Date date = book.getPublishYear();
        if(date != null){
            java.sql.Date sqlDate = new java.sql.Date(DateFormatter.parseDate(DateFormatter.format(date)).getTime());
            book.setPublishYear(sqlDate);
        }
        modelAndView.getModelMap().addAttribute("book", book);
    }
}
