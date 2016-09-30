package com.mywork.springboot1.controller;

import com.mywork.springboot1.dao.PesertaDao;
import com.mywork.springboot1.entity.Peserta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by israj on 9/28/2016.
 */

@Controller
@RequestMapping("/peserta")
public class PesertaHtmlController {

    @Autowired
    PesertaDao pd;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/list")
    public void daftarPeserta(Model m){
        m.addAttribute("daftarPeserta",pd.findAll());
    }

    @RequestMapping("/delete")
    public String hapusPeserta(@RequestParam("id") String id){
        System.out.println("===========================================");
        System.out.println("id ="+id);
        System.out.println("===========================================");
        pd.delete(id);
        return "redirect:list";
    }

    @RequestMapping( value = "/form", method = RequestMethod.GET)
    public String tampilForm(@RequestParam("id") String id,Model m){

        if (id !=null || !id.isEmpty()){
            Peserta p = pd.findOne(id);
            if (p !=null){
                m.addAttribute("peserta",p);
            }
        }

        return "/peserta/form";
    }

    @RequestMapping( value = "/form", method = RequestMethod.POST)
    public String prosesForm(@Valid Peserta peserta, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/peserta/form";
        }
        System.out.println("============================");
        System.out.println(peserta.getId());
        System.out.println(peserta.getName());
        System.out.println(peserta.getEmail());
        System.out.println(peserta.getTanggalLahir());
        System.out.println("============================");
        pd.save(peserta);
        return "redirect:list";
    }

}
