package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.CourseRatingsDao;
import com.appxbuild.nagpurit.dao.ReviewsDao;
import com.appxbuild.nagpurit.entity.CourseRatings;
import com.appxbuild.nagpurit.entity.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRatingsServiceImp implements CourseRatingsService {

    private CourseRatingsDao courseRatingsDao;

    @Autowired
    public CourseRatingsServiceImp(CourseRatingsDao courseRatingsDao) {
        this.courseRatingsDao = courseRatingsDao;
    }


    @Override
    public List<CourseRatings> findAll() {
        return courseRatingsDao.findAll();
    }

    @Override
    public CourseRatings findById(int theId) {
        Optional<CourseRatings> res = courseRatingsDao.findById(theId);
        CourseRatings ratings = null;
        if (res.isPresent()) {
            ratings = res.get();
        }
        else {
//            throw new RuntimeException("courseCategories id is not found" + theId);
            return new CourseRatings();
        }
        return ratings;
    }

    @Override
    public CourseRatings save(CourseRatings courseRatings) {
        return courseRatingsDao.save(courseRatings);
    }

    @Override
    public void deleteById(int theId) {
        courseRatingsDao.deleteById(theId);
    }
}
