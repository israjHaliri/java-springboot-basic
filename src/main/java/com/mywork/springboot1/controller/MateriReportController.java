package com.mywork.springboot1.controller;

import com.mywork.springboot1.dao.MateriDao;
import com.mywork.springboot1.entity.Materi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by israj on 10/3/2016.
 */

@Controller
public class MateriReportController {

    @Autowired
    private MateriDao materiDao;

    @RequestMapping("/materi")
    public ModelAndView generateReportPdf(ModelAndView m, @RequestParam(value = "format",required = false) String format){
        Iterable<Materi> data = materiDao.findAll();
        m.addObject("dataSource",data);
        m.addObject("tanggalUpdate",new Date());

        if (!format.isEmpty() || format !=null){
            m.addObject("format",format);
        }else{
            m.addObject("format","pdf");
        }

        m.setViewName("report_materi");

        return m;
    }

}
