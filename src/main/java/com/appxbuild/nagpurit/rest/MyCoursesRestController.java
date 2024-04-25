package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.MyCourses;
import com.appxbuild.nagpurit.service.MyCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyCoursesRestController {

    private MyCoursesService myCoursesService;

    @Autowired
    public MyCoursesRestController(MyCoursesService theMyCoursesService){
        myCoursesService = theMyCoursesService;
    }

    // add mapping GET("/myCourses") to get a list of MyCourses
    @GetMapping("/myCourses")
    public List<MyCourses> findAll(){
        return myCoursesService.findAll();
    }

    // add mapping GET("/myCourses/{id}") to get a list of MyCourses
    @GetMapping("/myCourses/{id}")
    public MyCourses getMyCourse(@PathVariable int id){
        MyCourses theMyCourses = myCoursesService.findById(id);
        if(theMyCourses==null){
            throw new RuntimeException("My Course is not found " + id);
        }
        return theMyCourses;
    }

    @PostMapping("/myCourses")
    public MyCourses addMyCourse(@RequestBody MyCourses theMyCourses){
        theMyCourses.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        theMyCourses.setCreated(localDateTime);
        theMyCourses.setModified(null);
        MyCourses newMyCourses = myCoursesService.save(theMyCourses);
        return newMyCourses;
    }

    @PutMapping("/myCourses")
    public MyCourses updateMyCourse(@RequestBody MyCourses theMyCourses){
        LocalDateTime localDateTime = LocalDateTime.now();
        theMyCourses.setModified(localDateTime);
        MyCourses newMyCourses = myCoursesService.save(theMyCourses);
        return newMyCourses;
    }

    @DeleteMapping("/myCourses/{id}")
    public String deleteMyCourse(@PathVariable int id){
        MyCourses theMyCourses = myCoursesService.findById(id);
        if(theMyCourses==null){
            throw new RuntimeException("My Course is not found " + id);
        }
        myCoursesService.deleteById(id);
        return "Deleted My Course id " + id;
    }

}
