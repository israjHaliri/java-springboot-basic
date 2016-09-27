package com.mywork.springboot1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by israj on 9/27/2016.
 */
@RestController
public class HaloController {

    @RequestMapping("/halo")
    public Map<String,Object> halo(
            @RequestParam(value = "nama",required = false) String nama
    ){
        Map<String,Object> map = new HashMap<>();
        map.put("waktu",new Date());
        map.put("nama",nama);

        return map;
    }
}
