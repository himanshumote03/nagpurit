package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.MyCourses;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyCoursesService {

    List<MyCourses> findAll();
    MyCourses findById(int theId);
    MyCourses save(MyCourses theMyCourses);
    void deleteById(int theId);

}
