package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.CourseRatings;
import com.appxbuild.nagpurit.entity.Reviews;
import com.appxbuild.nagpurit.service.CourseRatingsService;
import com.appxbuild.nagpurit.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseRatingsRestController {

    private CourseRatingsService courseRatingsService;

    @Autowired
    public CourseRatingsRestController(CourseRatingsService courseRatingsService) {
        this.courseRatingsService = courseRatingsService;
    }

    @GetMapping("/courseRatings")
    public List<CourseRatings> findAll(){
        return courseRatingsService.findAll();
    }

    @GetMapping("/courseRatings/{id}")
    public CourseRatings getCourseRatings(@PathVariable int id){
        CourseRatings theCourseRatings = courseRatingsService.findById(id);
        if (theCourseRatings==null){
            throw new RuntimeException("CourseRatings id is not found " + id);
        }
        return theCourseRatings;
    }


    @GetMapping("/courseRatings/login/{loginId}")
    public ResponseEntity<List<CourseRatings>> getCourseRatingsByLoginId(@PathVariable int loginId) {
        List<CourseRatings> courseRatings = courseRatingsService.findAll()
                .stream()
                .filter(u -> u.getLoginDetails() != null && u.getLoginDetails().getId() == loginId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courseRatings);
    }

    @GetMapping("/courseRatings/course/{courseId}")
    public ResponseEntity<List<CourseRatings>> getCourseRatingsByCourseId(@PathVariable int courseId) {
        List<CourseRatings> courseRatings = courseRatingsService.findAll()
                .stream()
                .filter(u -> u.getCourses() != null && u.getCourses().getId() == courseId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courseRatings);
    }

    @PostMapping("/courseRatings")
    public CourseRatings addCourseRatings(@RequestBody CourseRatings courseRatings) {
        courseRatings.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        courseRatings.setCreated(dt);
        CourseRatings newCourseRatings = courseRatingsService.save(courseRatings);
        return newCourseRatings;
    }

    @PutMapping("/courseRatings")
    public CourseRatings updateCourseRatings(@RequestBody CourseRatings courseRatings) {
        CourseRatings existingCourseRatings = courseRatingsService.findById(courseRatings.getId());

        if (existingCourseRatings == null) {
            throw new RuntimeException("Login Detail with id " + courseRatings.getId() + " not found");
        }
        courseRatings.setCreated(existingCourseRatings.getCreated());

        CourseRatings newCourseRatings = courseRatingsService.save(courseRatings);

        return newCourseRatings;
    }

    @DeleteMapping("/courseRatings/{id}")
    public String deleteCourseRatings(@PathVariable int id) {
        CourseRatings courseRatings = courseRatingsService.findById(id);
        if (courseRatings == null) {
            throw new RuntimeException("CourseRatings id is not found " + id);
        }
        courseRatingsService.deleteById(id);
        return ("Deleted CourseRatings id " + id);
    }

}
