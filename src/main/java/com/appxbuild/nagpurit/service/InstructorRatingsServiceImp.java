package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.CourseRatingsDao;
import com.appxbuild.nagpurit.dao.InstructorRatingsDao;
import com.appxbuild.nagpurit.entity.CourseRatings;
import com.appxbuild.nagpurit.entity.InstructorRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorRatingsServiceImp implements InstructorRatingsService {

    private InstructorRatingsDao instructorRatingsDao;

    @Autowired
    public InstructorRatingsServiceImp(InstructorRatingsDao instructorRatingsDao) {
        this.instructorRatingsDao = instructorRatingsDao;
    }


    @Override
    public List<InstructorRatings> findAll() {
        return instructorRatingsDao.findAll();
    }

    @Override
    public InstructorRatings findById(int theId) {
        Optional<InstructorRatings> res = instructorRatingsDao.findById(theId);
        InstructorRatings ratings = null;
        if (res.isPresent()) {
            ratings = res.get();
        }
        else {
//            throw new RuntimeException("courseCategories id is not found" + theId);
            return new InstructorRatings();
        }
        return ratings;
    }

    @Override
    public InstructorRatings save(InstructorRatings instructorRatings) {
        return instructorRatingsDao.save(instructorRatings);
    }

    @Override
    public void deleteById(int theId) {
        instructorRatingsDao.deleteById(theId);
    }
}
