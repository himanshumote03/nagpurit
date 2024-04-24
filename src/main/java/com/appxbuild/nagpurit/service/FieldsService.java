package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.Fields;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FieldsService {

    List<Fields> findAll();
    Fields findById(int theId);
    Fields save(Fields theFields);
    void deleteById(int theId);

}
