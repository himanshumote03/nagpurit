package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.CourseCategories;
import com.appxbuild.nagpurit.service.CourseCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseCategoriesRestController {

    private CourseCategoriesService courseCategoriesService;

    @Autowired
    public CourseCategoriesRestController(CourseCategoriesService courseCategoriesService) {
        this.courseCategoriesService = courseCategoriesService;
    }

    @GetMapping("/courseCategories")
    public List<CourseCategories> findAll() {
        return courseCategoriesService.findAll();
    }

    @PostMapping("/courseCategories")
    public CourseCategories addCheckIn(@RequestBody CourseCategories courseCategories) {
//        courseCategories.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        courseCategories.setCreated(dt);
        CourseCategories newCourseCategories1 = courseCategoriesService.save(courseCategories);
        return newCourseCategories1;
    }

    @PutMapping("/courseCategories")
    public CourseCategories updateCheckIn(@RequestBody CourseCategories courseCategories) {
        CourseCategories existingCourseCategories = courseCategoriesService.findById(courseCategories.getId());

        if (existingCourseCategories == null) {
            throw new RuntimeException("Login Detail with id " + courseCategories.getId() + " not found");
        }
        courseCategories.setCreated(existingCourseCategories.getCreated());

        LocalDateTime localDateTime = LocalDateTime.now();
        courseCategories.setModified(localDateTime);

        CourseCategories newCourseCategories = courseCategoriesService.save(courseCategories);

        return newCourseCategories;
    }

    @DeleteMapping("/courseCategories/{id}")
    public String deleteCheckIn(@PathVariable int id) {
        CourseCategories courseCategories = courseCategoriesService.findById(id);
        if (courseCategories == null) {
            throw new RuntimeException("CourseCategories id is not found " + id);
        }
        courseCategoriesService.deleteById(id);
        return ("Deleted CourseCategories id " + id);
    }

}
