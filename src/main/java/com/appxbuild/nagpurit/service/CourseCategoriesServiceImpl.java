package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.CourseCategoriesDao;
import com.appxbuild.nagpurit.entity.CheckOut;
import com.appxbuild.nagpurit.entity.CourseCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseCategoriesServiceImpl implements CourseCategoriesService {

    private CourseCategoriesDao courseCategoriesDao;

    @Autowired
    public CourseCategoriesServiceImpl(CourseCategoriesDao courseCategoriesDao) {
        this.courseCategoriesDao = courseCategoriesDao;
    }


    @Override
    public List<CourseCategories> findAll() {
        return courseCategoriesDao.findAll();
    }

    @Override
    public CourseCategories findById(int theId) {
        Optional<CourseCategories> res = courseCategoriesDao.findById(theId);
        CourseCategories courseCategories = null;
        if (res.isPresent()) {
            courseCategories = res.get();
        }
        else {
//            throw new RuntimeException("courseCategories id is not found" + theId);
            return new CourseCategories();
        }
        return courseCategories;
    }

    @Override
    public CourseCategories save(CourseCategories courseCategories) {
        return courseCategoriesDao.save(courseCategories);
    }

    @Override
    public void deleteById(int theId) {
        courseCategoriesDao.deleteById(theId);
    }
}
