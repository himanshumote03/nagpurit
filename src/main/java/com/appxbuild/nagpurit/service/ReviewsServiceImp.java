package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.ReviewsDao;
import com.appxbuild.nagpurit.entity.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceImp implements ReviewsService {

    private ReviewsDao reviewsDao;

   @Autowired
    public ReviewsServiceImp(ReviewsDao reviewsDao) {
        this.reviewsDao = reviewsDao;
    }


    @Override
    public List<Reviews> findAll() {
        return reviewsDao.findAll();
    }

    @Override
    public Reviews findById(int theId) {
        Optional<Reviews> res = reviewsDao.findById(theId);
        Reviews reviews = null;
        if (res.isPresent()) {
            reviews = res.get();
        }
        else {
//            throw new RuntimeException("courseCategories id is not found" + theId);
            return new Reviews();
        }
        return reviews;
    }

    @Override
    public Reviews save(Reviews reviews) {
        return reviewsDao.save(reviews);
    }

    @Override
    public void deleteById(int theId) {
        reviewsDao.deleteById(theId);
    }
}
