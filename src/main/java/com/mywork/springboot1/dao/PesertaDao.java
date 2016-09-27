package com.mywork.springboot1.dao;

import com.mywork.springboot1.entity.Peserta;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by israj on 9/22/2016.
 */
public interface PesertaDao extends PagingAndSortingRepository<Peserta, String> {
}
