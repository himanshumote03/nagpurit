package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.CourseRatings;
import com.appxbuild.nagpurit.entity.InstructorRatings;

import java.util.List;

public interface InstructorRatingsService {

    List<InstructorRatings> findAll();

    InstructorRatings findById(int theId);

    InstructorRatings save(InstructorRatings instructorRatings);

    void deleteById(int theId);

}
