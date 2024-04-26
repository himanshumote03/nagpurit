package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SectionService {

    List<Section> findAll();
    Section findById(int theId);
    Section save(Section section);
    void deleteById(int theId);

}
