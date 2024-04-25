package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.CourseVideos;

import java.util.List;

public interface CourseVideosService {

    List<CourseVideos> findAll();
    CourseVideos findById(int id);
    CourseVideos save(CourseVideos courseVideos);
    void deleteById(int id);
}
