package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.CourseRatings;
import com.appxbuild.nagpurit.entity.Reviews;

import java.util.List;

public interface CourseRatingsService {

    List<CourseRatings> findAll();

    CourseRatings findById(int theId);

    CourseRatings save(CourseRatings courseRatings);

    void deleteById(int theId);

}
