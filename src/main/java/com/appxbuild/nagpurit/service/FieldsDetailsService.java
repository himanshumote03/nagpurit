package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.FieldsDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FieldsDetailsService {

    List<FieldsDetails> findAll();
    FieldsDetails findById(int theId);
    FieldsDetails save(FieldsDetails theFieldDetails);
    void  deleteById(int theId);

}
