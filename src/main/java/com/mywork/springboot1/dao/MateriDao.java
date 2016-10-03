package com.mywork.springboot1.dao;

import com.mywork.springboot1.entity.Materi;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by israj on 10/3/2016.
 */
public interface MateriDao extends PagingAndSortingRepository<Materi, String> {
}
