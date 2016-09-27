package com.mywork.springboot1.controller;

import com.mywork.springboot1.dao.PesertaDao;
import com.mywork.springboot1.entity.Peserta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by israj on 9/27/2016.
 */
@RestController
public class PesertaController {

    @Autowired
    private PesertaDao pd;

    @RequestMapping(value = "/peserta",method = RequestMethod.GET)
    public Page<Peserta> cariPeserta(Pageable page){
        return pd.findAll(page);
    }

    @RequestMapping(value = "/peserta",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertPeserta(@RequestBody Peserta peserta){
        pd.save(peserta);
    }

    @RequestMapping(value = "/peserta/{id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updatePeserta(@PathVariable("id") String id,@RequestBody Peserta peserta){
        peserta.setId(id);
        pd.save(peserta);
    }


    @RequestMapping(value = "/peserta/{id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Peserta> cariPesertaById(@PathVariable("id") String id){
        Peserta hasil = pd.findOne(id);
        if (hasil == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<Peserta>(hasil,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/peserta/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void hapusPeserta(@PathVariable("id") String id){
        pd.delete(id);
    }
}
