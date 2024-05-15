package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.InstructorReviews;

import java.util.List;

public interface InstructorReviewsService {

    List<InstructorReviews> findAll();

    InstructorReviews findById(int theId);

    InstructorReviews save(InstructorReviews reviews);

    void deleteById(int theId);

}
