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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by israj on 9/28/2016.
 */

@Controller
@RequestMapping("/backend/peserta")
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
    public String tampilForm(@RequestParam(value = "id",required = false) String id,Model m){

        m.addAttribute("peserta",new Peserta());
        if (id !=null && !id.isEmpty()){
            Peserta p = pd.findOne(id);
            if (p !=null){
                m.addAttribute("peserta",p);
            }
        }else{

        }
        return "/peserta/form";

    }

    @RequestMapping( value = "/form", method = RequestMethod.POST)
    public String prosesForm(
            @Valid Peserta peserta,
            BindingResult bindingResult,
            MultipartFile foto,
            HttpSession session) throws Exception
    {
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

        String namaFile = foto.getName();
        String jenisFile = foto.getContentType();
        String namaAsli = foto.getOriginalFilename();
        Long ukuran = foto.getSize();

        System.out.println("===========================");
        System.out.println(namaFile);
        System.out.println(jenisFile);
        System.out.println(namaAsli);
        System.out.println(ukuran);
        System.out.println("===========================");

        String lokasiPath = "/upload";
        String lokasiTomcat = session.getServletContext().getRealPath(lokasiPath);
        System.out.println("lokasi tomcat di jalankan :"+lokasiTomcat);
//        String lokasiTujuan = lokasiTomcat + File.separator;

//        File folderTujuan =  new File(lokasiTomcat + File.separator);
        String homeFolder = System.getProperty("user.home");
        String lokasiTujuan = homeFolder + File.separator + "tmp";
        File folderTujuan =  new File(lokasiTujuan);
        if (!folderTujuan.exists()){
            folderTujuan.mkdirs();
        }

        File tujuan = new File(lokasiTujuan + File.separator + namaAsli);

        foto.transferTo(tujuan);
        System.out.println("file sudah di copy ke : "+tujuan.getAbsolutePath());

        return "redirect:list";
    }

}
