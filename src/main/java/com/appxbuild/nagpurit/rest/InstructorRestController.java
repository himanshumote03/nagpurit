package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.dao.InstructorDao;
import com.appxbuild.nagpurit.dao.UserDao;
import com.appxbuild.nagpurit.dto.InstructorDto;
import com.appxbuild.nagpurit.dto.UserDto;
import com.appxbuild.nagpurit.entity.Instructor;
import com.appxbuild.nagpurit.entity.User;
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
public class InstructorRestController {

    private InstructorDao instructorDao;

    @Autowired
    public InstructorRestController(InstructorDao theInstructorDao) {
        this.instructorDao = theInstructorDao;
    }

    // add mapping GET("/instructor") to get a list of Instructor
    @GetMapping("/instructor")
    public List<Instructor> findAll(){
        return instructorDao.findAll();
    }

    // add mapping GET("/instructor/{id}") to get a Instructor
    @GetMapping("/instructor/{id}")
    public ResponseEntity<Instructor> getCourse(@PathVariable int id) {
        Optional<Instructor> theInstructor = instructorDao.findById(id);
        return theInstructor.map(instructor -> new ResponseEntity<>(instructor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // add mapping GET("/instructor/{id}/image") to get Instructor image
    @GetMapping("/instructor/{id}/image")
    public ResponseEntity<byte[]> getInstructorImage(@PathVariable int id) throws IOException {
        Optional<Instructor> theInstructor = instructorDao.findById(id);
        if (theInstructor.isPresent()) {
            Instructor instructor = theInstructor.get();
            Path imagePath = Path.of("public/images/", instructor.getImage()); // Adjust the path as needed
            if (Files.exists(imagePath)) {
                byte[] imageBytes = Files.readAllBytes(imagePath);
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
            }
        }
        // Return 404 if the product or image does not exist
        return ResponseEntity.notFound().build();
    }

    // add mapping POST("/instructor") to add an Instructor
    @PostMapping("/instructor")
    public ResponseEntity<String> addInstructor(
            @Valid @ModelAttribute InstructorDto instructorDto,
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

        // create and save instructor

        Instructor instructor = new Instructor();
        instructor.setId(instructorDto.getId());
        instructor.setImage(storageFileName);
        instructor.setName(instructorDto.getName());
        instructor.setTotalStudents(instructorDto.getTotalStudents());
        instructor.setReviews(instructorDto.getReviews());
        instructor.setDescription(instructorDto.getDescription());
        instructor.setGithubUrl(instructorDto.getGithubUrl());
        instructor.setLinkedinUrl(instructorDto.getLinkedinUrl());
        instructor.setCreated(localDateTime);
        instructor.setModified(null);
        instructorDao.save(instructor);

        return new ResponseEntity<>("Instructor added successfully", HttpStatus.CREATED);
    }


    // add mapping PUT("/instructor/{id}") to update an existing Instructor
    @PutMapping("/instructor/{id}")
    public ResponseEntity<String> updateInstructor(
            @PathVariable int id,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @Valid @ModelAttribute InstructorDto instructorDto,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        Optional<Instructor> theInstructor = instructorDao.findById(id);
        if (theInstructor.isPresent()) {
            Instructor existingInstructor = theInstructor.get();
            LocalDateTime localDateTime = LocalDateTime.now();
            String storageFileName = localDateTime.getMinute() + "_" + imageFile.getOriginalFilename();

            // Update instructor information
            existingInstructor.setId(instructorDto.getId());
            existingInstructor.setImage(storageFileName);
            existingInstructor.setName(instructorDto.getName());
            existingInstructor.setTotalStudents(instructorDto.getTotalStudents());
            existingInstructor.setReviews(instructorDto.getReviews());
            existingInstructor.setDescription(instructorDto.getDescription());
            existingInstructor.setGithubUrl(instructorDto.getGithubUrl());
            existingInstructor.setLinkedinUrl(instructorDto.getLinkedinUrl());
            existingInstructor.setModified(localDateTime);
            instructorDao.save(existingInstructor);

            // Handle imageFile if provided
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String uploadDir = "public/images/";
                    Path uploadPath = Paths.get(uploadDir);

                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    // Delete old image file
                    if (existingInstructor.getImage() != null) {
                        Path oldImagePath = Paths.get(uploadDir + existingInstructor.getImage());
                        Files.deleteIfExists(oldImagePath);
                    }

                    // Save new image file
                    String storageFile = localDateTime.getMinute() + "_" + imageFile.getOriginalFilename();
                    Path filePath = Paths.get(uploadDir + storageFile);
                    Files.copy(imageFile.getInputStream(), filePath);
                    existingInstructor.setImage(storageFile);
                } catch (IOException ex) {
                    return new ResponseEntity<>("Failed to upload image file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            instructorDao.save(existingInstructor);
            return new ResponseEntity<>("Instructor updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Instructor id is not found", HttpStatus.NOT_FOUND);
        }
    }


    // add mapping DELETE("/instructor/{id}") to get a list of Instructor
    @DeleteMapping("/instructor/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable int id) {
        Optional<Instructor> theInstructor = instructorDao.findById(id);
        if (theInstructor.isPresent()) {
            Instructor instructor = theInstructor.get();
            instructorDao.delete(instructor);
            return new ResponseEntity<>("Instructor id deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Instructor id is not found", HttpStatus.NOT_FOUND);
        }
    }

}
