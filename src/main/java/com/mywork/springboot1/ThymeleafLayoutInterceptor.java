package com.mywork.springboot1;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by israj on 10/3/2016.
 */
public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {


    private static final String DEFAULT_LAYOUT = "layout";
    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getAuthorities().toString();

        System.out.println("============== jenis login ==================");
        System.out.println(name);
        System.out.println("============== jenis login ==================");

        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }
        String originalViewName = modelAndView.getViewName();

        if (originalViewName.startsWith("redirect:")){
            return;
        }
        modelAndView.setViewName(DEFAULT_LAYOUT);
        modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);
    }
}