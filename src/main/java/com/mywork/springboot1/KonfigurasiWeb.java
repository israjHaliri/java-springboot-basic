package com.mywork.springboot1;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by israj on 9/30/2016.
 */

@Configuration
public class KonfigurasiWeb extends WebMvcConfigurerAdapter{

    @Override
    public  void addViewControllers(ViewControllerRegistry viewControllerRegistry){
        viewControllerRegistry.addViewController("/login").setViewName("login");
    }
}
