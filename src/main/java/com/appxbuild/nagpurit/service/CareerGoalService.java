package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.entity.CareerGoal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CareerGoalService {

    List<CareerGoal> findAll();
    CareerGoal findById(int theId);
    CareerGoal save(CareerGoal theCareerGoal);
    void deleteById(int theId);

}
