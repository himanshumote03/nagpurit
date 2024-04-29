package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.CheckIn;
import com.appxbuild.nagpurit.entity.MyCourses;
import com.appxbuild.nagpurit.service.MyCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/myCourses/login/{loginId}")
    public ResponseEntity<List<MyCourses>> getMyCoursesByLoginId(@PathVariable int loginId) {
        List<MyCourses> theMyCourses = myCoursesService.findAll()
                .stream()
                .filter(myCourses -> myCourses.getLoginDetails() != null && myCourses.getLoginDetails().getId() == loginId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(theMyCourses);
    }

    // add mapping POST("/myCourses) to add a MyCourses
    @PostMapping("/myCourses")
    public MyCourses addMyCourse(@RequestBody MyCourses theMyCourses){
        theMyCourses.setId(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        theMyCourses.setCreated(localDateTime);
        theMyCourses.setModified(null);
        MyCourses newMyCourses = myCoursesService.save(theMyCourses);
        return newMyCourses;
    }

    // add mapping PUT("/myCourses) to update an existing MyCourses
    @PutMapping("/myCourses")
    public MyCourses updateMyCourse(@RequestBody MyCourses theMyCourses){
        LocalDateTime localDateTime = LocalDateTime.now();
        theMyCourses.setModified(localDateTime);
        MyCourses newMyCourses = myCoursesService.save(theMyCourses);
        return newMyCourses;
    }

    // add mapping DELETE("/myCourses/{id}") to delete a MyCourses
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
