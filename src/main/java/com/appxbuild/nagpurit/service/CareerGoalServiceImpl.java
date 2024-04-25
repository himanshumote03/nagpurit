package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.CareerGoalDao;
import com.appxbuild.nagpurit.entity.CareerGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareerGoalServiceImpl implements CareerGoalService {

    private CareerGoalDao careerGoalDao;

    @Autowired
    public CareerGoalServiceImpl(CareerGoalDao theCareerGoalDao){
        careerGoalDao = theCareerGoalDao;
    }

    @Override
    public List<CareerGoal> findAll() {
        return careerGoalDao.findAll();
    }

    @Override
    public CareerGoal findById(int theId) {
        Optional<CareerGoal> result = careerGoalDao.findById(theId);
        CareerGoal theCareerGoal = null;
        if (result.isPresent()){
            theCareerGoal = result.get();
        }
        else {
            throw new RuntimeException("Career Goal id is not found " + theId);
        }
        return theCareerGoal;
    }

    @Override
    public CareerGoal save(CareerGoal theCareerGoal) {
        return careerGoalDao.save(theCareerGoal);
    }

    @Override
    public void deleteById(int theId) {
        careerGoalDao.deleteById(theId);
    }
}
