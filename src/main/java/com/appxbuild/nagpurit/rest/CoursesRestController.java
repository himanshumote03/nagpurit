package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.dao.CoursesDao;
import com.appxbuild.nagpurit.driveService.CourseService;
import com.appxbuild.nagpurit.dto.CoursesDto;
import com.appxbuild.nagpurit.entity.Courses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CoursesRestController {

    private CoursesDao coursesDao;
    private CourseService courseService;

    @Autowired
    public CoursesRestController(CoursesDao coursesDao, CourseService courseService) {
        this.coursesDao = coursesDao;
        this.courseService = courseService;
    }


    // add mapping GET("/courses") to get a list of Courses
    @GetMapping("/courses")
    public List<Courses> findAll(){
        return coursesDao.findAll();
    }

    // add mapping GET("/courses/{id}") to get a Course
    @GetMapping("/courses/{id}")
    public ResponseEntity<Courses> getCourse(@PathVariable int id) {
        Optional<Courses> theCourses = coursesDao.findById(id);
        return theCourses.map(courses -> new ResponseEntity<>(courses, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // add mapping POST("/courses") to add a Course
    @PostMapping("/courses")
    public ResponseEntity<String> addCourse(
            @Valid @ModelAttribute CoursesDto coursesDto,
            @RequestParam("image") MultipartFile image,
            BindingResult result
    ) throws IOException, GeneralSecurityException {
        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        // Save image file
        if (image.isEmpty()) {
            return new ResponseEntity<>("Image file is required", HttpStatus.BAD_REQUEST);
        }

        try {
            // Upload image to Google Drive and get image URL
            File tempFile = File.createTempFile("course", null);
            image.transferTo(tempFile);
            String imageUrl = courseService.uploadImageToDrive(tempFile);

            if (imageUrl == null || imageUrl.isEmpty()) {
                return new ResponseEntity<>("Failed to upload image to Google Drive", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Create a new User entity and set user details

            Courses courses = new Courses();
            courses.setId(coursesDto.getId());
            courses.setImage(imageUrl);  // Set the image URL
            courses.setCourseCategories(coursesDto.getCourseCategories());
            courses.setCourseTitle(coursesDto.getCourseTitle());
            courses.setDescription(coursesDto.getDescription());
            courses.setRatings(coursesDto.getRatings());
            courses.setLanguage(coursesDto.getLanguage());
            courses.setSubTitle(coursesDto.getSubTitle());
            courses.setCost(coursesDto.getCost());
            courses.setCourseOutcome(coursesDto.getCourseOutcome());
            courses.setInstructor(coursesDto.getInstructor());
            courses.setCreated(LocalDateTime.now());
            courses.setModified(null);

            // save course to database
            coursesDao.save(courses);

            return new ResponseEntity<>("Course added successfully", HttpStatus.CREATED);

        } catch (IOException e) {
            return new ResponseEntity<>("Error processing image upload: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // add mapping PUT("/courses") to update an existing Course
    @PutMapping("/courses")
    public ResponseEntity<String> updateCourse(
            @RequestParam(value = "image", required = false) MultipartFile image,
            @Valid @ModelAttribute CoursesDto coursesDto,
            BindingResult result
    ) throws IOException, GeneralSecurityException {

        Integer courseId = coursesDto.getId();
        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        // Check if the Course ID is provided
        if (courseId == null) {
            return new ResponseEntity<>("Course ID is required for updating", HttpStatus.BAD_REQUEST);
        }

        Optional<Courses> optionalCourses = coursesDao.findById(coursesDto.getId());

        if (optionalCourses.isEmpty()) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        Courses existingCourse = optionalCourses.get();

        // Update course information
        existingCourse.setId(coursesDto.getId());
//        existingCourse.setImage(coursesDt);
        existingCourse.setCourseCategories(coursesDto.getCourseCategories());
        existingCourse.setCourseTitle(coursesDto.getCourseTitle());
        existingCourse.setDescription(coursesDto.getDescription());
        existingCourse.setRatings(coursesDto.getRatings());
        existingCourse.setLanguage(coursesDto.getLanguage());
        existingCourse.setSubTitle(coursesDto.getSubTitle());
        existingCourse.setCost(coursesDto.getCost());
        existingCourse.setCourseOutcome(coursesDto.getCourseOutcome());
        existingCourse.setInstructor(coursesDto.getInstructor());
        existingCourse.setCreated(existingCourse.getCreated());

        coursesDao.save(existingCourse);
        // Handle imageFile if provided
        if (image != null && !image.isEmpty()) {
            try {
                File tempFile = File.createTempFile("course", null);
                image.transferTo(tempFile);
                String imageUrl = courseService.uploadImageToDrive(tempFile);

                if (imageUrl == null || imageUrl.isEmpty()) {
                    return new ResponseEntity<>("Failed to upload image to Google Drive", HttpStatus.INTERNAL_SERVER_ERROR);
                }

                // Update course image URL
                existingCourse.setImage(imageUrl);
            } catch (IOException ex) {
                return new ResponseEntity<>("Error processing image upload: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        // Update modified timestamp
        existingCourse.setModified(LocalDateTime.now());

        // Save updated instructor
        coursesDao.save(existingCourse);

        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
    }


    // add mapping DELETE("/courses/{id}") to delete a Course
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        Optional<Courses> theCourses = coursesDao.findById(id);
        if (theCourses.isPresent()) {
            Courses courses = theCourses.get();
            coursesDao.delete(courses);
            return new ResponseEntity<>("Course id deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Course id is not found", HttpStatus.NOT_FOUND);
        }
    }

}
