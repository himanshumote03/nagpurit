package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.Reviews;

import java.util.List;

public interface ReviewsService {

    List<Reviews> findAll();

    Reviews findById(int theId);

    Reviews save(Reviews reviews);

    void deleteById(int theId);

}
