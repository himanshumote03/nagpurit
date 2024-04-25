package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.CourseVideosDao;
import com.appxbuild.nagpurit.entity.CourseVideos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseVideosServiceImpl implements CourseVideosService {

    private CourseVideosDao courseVideosDao;

    public CourseVideosServiceImpl(CourseVideosDao courseVideosDao) {
        this.courseVideosDao = courseVideosDao;
    }

    public List<CourseVideos> findAll() {
        return courseVideosDao.findAll();
    }

    public CourseVideos findById(int id) {
        Optional<CourseVideos> result = courseVideosDao.findById(id);
        CourseVideos courseVideos = null;
        if(result.isPresent()){
            courseVideos = result.get();
        }
        else {
            throw  new RuntimeException("Field Details id is not found " + id);
        }
        return courseVideos;
    }

    @Override
    public CourseVideos save(CourseVideos courseVideos) {
        return courseVideosDao.save(courseVideos);
    }

    @Override
    public void deleteById(int id) {
        courseVideosDao.deleteById(id);
    }

}
