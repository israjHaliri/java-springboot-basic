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


    private static final String DEFAULT_LAYOUT_FRONTEND = "layout";
    private static final String DEFAULT_LAYOUT_BACKEND = "layout-backend";
    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }
        String originalViewName = modelAndView.getViewName();

        if (originalViewName.startsWith("redirect:")){
            return;
        }

        if (request.getRequestURI().startsWith("/backend")){
            System.out.println("jalan di backedn");
            System.out.println(request.getRequestURI());
            modelAndView.setViewName(DEFAULT_LAYOUT_BACKEND);
        }
        else{
            System.out.println("jalan disnin");
            System.out.println(request.getRequestURI());
            modelAndView.setViewName(DEFAULT_LAYOUT_FRONTEND);
        }
        modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);
    }
}