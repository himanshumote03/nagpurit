package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.CourseCategories;

import java.util.List;

public interface CourseCategoriesService {

    List<CourseCategories> findAll();

    CourseCategories findById(int theId);

    CourseCategories save(CourseCategories courseCategories);

    void deleteById(int theId);

}
