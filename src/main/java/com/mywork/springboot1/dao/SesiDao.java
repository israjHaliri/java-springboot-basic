package com.mywork.springboot1.dao;

import com.mywork.springboot1.entity.Materi;
import com.mywork.springboot1.entity.Sesi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by israj on 9/26/2016.
 */
public interface SesiDao extends PagingAndSortingRepository<Sesi,String> {
    public Page<Sesi> findByMateri(Materi m, Pageable pageable);

    @Query("select x from Sesi x where x.mulai >= :m  " +
            "and x.mulai < :s " +
            "and x.materi.kode = :k " +
            "order by x.mulai desc")
    public Page<Sesi> cariBerdasarkanCustom(
            @Param("m") Date mulai,
            @Param("s") Date sampai,
            @Param("k") String Kode,
            Pageable pageable);
}
