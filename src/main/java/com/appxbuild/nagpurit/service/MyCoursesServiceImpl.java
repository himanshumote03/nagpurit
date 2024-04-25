package com.appxbuild.nagpurit.service;

import com.appxbuild.nagpurit.dao.MyCoursesDao;
import com.appxbuild.nagpurit.entity.MyCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyCoursesServiceImpl implements MyCoursesService{

    private MyCoursesDao myCoursesDao;

    @Autowired
    public MyCoursesServiceImpl(MyCoursesDao theMyCoursesDao){
        myCoursesDao = theMyCoursesDao;
    }

    @Override
    public List<MyCourses> findAll() {
        return myCoursesDao.findAll();
    }

    @Override
    public MyCourses findById(int theId) {
        Optional<MyCourses> result = myCoursesDao.findById(theId);
        MyCourses theMyCourses = null;
        if(result.isPresent()){
            theMyCourses = result.get();
        }
        else {
            throw new RuntimeException("My Course id is not found " + theId);
        }
        return theMyCourses;
    }

    @Override
    public MyCourses save(MyCourses theMyCourses) {
        return myCoursesDao.save(theMyCourses);
    }

    @Override
    public void deleteById(int theId) {
        myCoursesDao.deleteById(theId);
    }
}
