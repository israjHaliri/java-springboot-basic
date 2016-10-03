package com.mywork.springboot1;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

/**
 * Created by israj on 9/30/2016.
 */

@Configuration
public class KonfigurasiWeb extends WebMvcConfigurerAdapter{

    @Override
    public  void addViewControllers(ViewControllerRegistry viewControllerRegistry){
        viewControllerRegistry.addViewController("/login").setViewName("login");
    }


    @Bean
    public JasperReportsViewResolver getJasperReportsViewResolver() {
        JasperReportsViewResolver resolver = new JasperReportsViewResolver();

        resolver.setViewClass(JasperReportsMultiFormatView.class);
        resolver.setPrefix("classpath:/report/");
        resolver.setSuffix(".jrxml");
        resolver.setViewNames("report_*");
        resolver.setOrder(0);

        return resolver;
    }
}
