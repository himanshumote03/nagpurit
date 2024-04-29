package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.dao.CoursesDao;
import com.appxbuild.nagpurit.dto.CoursesDto;
import com.appxbuild.nagpurit.entity.Courses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CoursesRestController {

    private CoursesDao coursesDao;

    @Autowired
    public CoursesRestController(CoursesDao theCoursesDao){
        coursesDao = theCoursesDao;
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

    // add mapping GET("/courses/{id}/image") to get Course image
    @GetMapping("/courses/image/{id}")
    public ResponseEntity<byte[]> getCourseImage(@PathVariable int id) throws IOException {
        Optional<Courses> theCourses = coursesDao.findById(id);
        if (theCourses.isPresent()) {
            Courses courses = theCourses.get();
            Path imagePath = Path.of("public/images/", courses.getImage()); // Adjust the path as needed
            if (Files.exists(imagePath)) {
                byte[] imageBytes = Files.readAllBytes(imagePath);
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
            }
        }
        // Return 404 if the product or image does not exist
        return ResponseEntity.notFound().build();
    }

    // add mapping POST("/courses") to add a Course
    @PostMapping("/courses")
    public ResponseEntity<String> addCourse(
            @Valid @ModelAttribute CoursesDto coursesDto,
            @RequestParam("imageFile") MultipartFile imageFile,
            BindingResult result){
        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        // Save image file
        if (imageFile.isEmpty()) {
            return new ResponseEntity<>("Image file is required", HttpStatus.BAD_REQUEST);
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        String storageFileName = localDateTime.getMinute() + "_" + imageFile.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (var inputStream = imageFile.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName));
            }
        } catch (IOException ex) {
            return new ResponseEntity<>("Failed to save image file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // create and save course

        Courses courses = new Courses();
        courses.setId(coursesDto.getId());
        courses.setImage(storageFileName);
        courses.setCourseCategories(coursesDto.getCourseCategories());
        courses.setCourseTitle(coursesDto.getCourseTitle());
        courses.setDescription(coursesDto.getDescription());
        courses.setRatings(coursesDto.getRatings());
        courses.setLanguage(coursesDto.getLanguage());
        courses.setSubTitle(coursesDto.getSubTitle());
        courses.setCost(coursesDto.getCost());
        courses.setCourseOutcome(coursesDto.getCourseOutcome());
        courses.setInstructor(coursesDto.getInstructor());
        courses.setCreated(localDateTime);
        courses.setModified(null);
        coursesDao.save(courses);

        return new ResponseEntity<>("Course added successfully", HttpStatus.CREATED);
    }


    // add mapping PUT("/courses/{id}") to update an existing Course
    @PutMapping("/courses/{id}")
    public ResponseEntity<String> updateCourse(
            @PathVariable int id,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @Valid @ModelAttribute CoursesDto coursesDto,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        Optional<Courses> theCourses = coursesDao.findById(id);
        if (theCourses.isPresent()) {
            Courses existingCourse = theCourses.get();
            LocalDateTime localDateTime = LocalDateTime.now();
            String storageFileName = localDateTime.getMinute() + "_" + imageFile.getOriginalFilename();

            // Update course information
            existingCourse.setId(coursesDto.getId());
            existingCourse.setImage(storageFileName);
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
            existingCourse.setModified(localDateTime);
            coursesDao.save(existingCourse);

            // Handle imageFile if provided
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String uploadDir = "public/images/";
                    Path uploadPath = Paths.get(uploadDir);

                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    // Delete old image file
                    if (existingCourse.getImage() != null) {
                        Path oldImagePath = Paths.get(uploadDir + existingCourse.getImage());
                        Files.deleteIfExists(oldImagePath);
                    }

                    // Save new image file
                    String storageFile = localDateTime.getMinute() + "_" + imageFile.getOriginalFilename();
                    Path filePath = Paths.get(uploadDir + storageFile);
                    Files.copy(imageFile.getInputStream(), filePath);
                    existingCourse.setImage(storageFile);
                } catch (IOException ex) {
                    return new ResponseEntity<>("Failed to upload image file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            coursesDao.save(existingCourse);
            return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Course id is not found", HttpStatus.NOT_FOUND);
        }
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
