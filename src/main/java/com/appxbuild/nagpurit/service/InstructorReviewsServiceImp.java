package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.InstructorReviewsDao;
import com.appxbuild.nagpurit.dao.ReviewsDao;
import com.appxbuild.nagpurit.entity.InstructorReviews;
import com.appxbuild.nagpurit.entity.Reviews;
import org.checkerframework.checker.guieffect.qual.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorReviewsServiceImp implements InstructorReviewsService {

    private InstructorReviewsDao instructorReviewsDao;

    @Autowired
    public InstructorReviewsServiceImp(InstructorReviewsDao instructorReviewsDao) {
        this.instructorReviewsDao = instructorReviewsDao;
    }


    @Override
    public List<InstructorReviews> findAll() {
        return instructorReviewsDao.findAll();
    }

    @Override
    public InstructorReviews findById(int theId) {
        Optional<InstructorReviews> res = instructorReviewsDao.findById(theId);
        InstructorReviews reviews = null;
        if (res.isPresent()) {
            reviews = res.get();
        }
        else {
//            throw new RuntimeException("courseCategories id is not found" + theId);
            return new InstructorReviews();
        }
        return reviews;
    }

    @Override
    public InstructorReviews save(InstructorReviews reviews) {
        return instructorReviewsDao.save(reviews);
    }

    @Override
    public void deleteById(int theId) {
        instructorReviewsDao.deleteById(theId);
    }
}
