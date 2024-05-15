package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.dao.InstructorDao;
import com.appxbuild.nagpurit.driveService.InstructorService;
import com.appxbuild.nagpurit.dto.InstructorDto;
import com.appxbuild.nagpurit.entity.Instructor;
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
public class InstructorRestController {

    private InstructorDao instructorDao;
    private InstructorService instructorService;

    @Autowired
    public InstructorRestController(InstructorDao instructorDao, InstructorService instructorService) {
        this.instructorDao = instructorDao;
        this.instructorService = instructorService;
    }


    // add mapping GET("/instructor") to get a list of Instructor
    @GetMapping("/instructor")
    public List<Instructor> findAll() {
        return instructorDao.findAll();
    }

    // add mapping GET("/instructor/{id}") to get a Instructor
    @GetMapping("/instructor/{id}")
    public Optional<Instructor> getInstructor(@PathVariable int id) {
        Optional<Instructor> theInstructor = instructorDao.findById(id);
        if (theInstructor.isEmpty()) {
            return Optional.of(new Instructor());
        }
        return theInstructor;
    }
//    public ResponseEntity<Instructor> getInstructor(@PathVariable int id) {
//        Optional<Instructor> theInstructor = instructorDao.findById(id);
//        return theInstructor.map(instructor -> new ResponseEntity<>(instructor, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }


    // add mapping POST("/instructor") to add an Instructor
    @PostMapping("/instructor")
    public ResponseEntity<String> addInstructor(
            @Valid @ModelAttribute InstructorDto instructorDto,
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
            File tempFile = File.createTempFile("instructor", null);
            image.transferTo(tempFile);
            String imageUrl = instructorService.uploadImageToDrive(tempFile);

            if (imageUrl == null || imageUrl.isEmpty()) {
                return new ResponseEntity<>("Failed to upload image to Google Drive", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            // Create a new Instructor entity and set user details
            Instructor instructor = new Instructor();
            instructor.setId(instructorDto.getId());
            instructor.setImage(imageUrl);
            instructor.setName(instructorDto.getName());
            instructor.setDesignation(instructorDto.getDesignation());
            instructor.setTotalStudents(instructorDto.getTotalStudents());
            instructor.setDescription(instructorDto.getDescription());
            instructor.setGithubUrl(instructorDto.getGithubUrl());
            instructor.setLinkedinUrl(instructorDto.getLinkedinUrl());
            instructor.setCreated(LocalDateTime.now());
            instructor.setModified(null);
            instructorDao.save(instructor);

            return new ResponseEntity<>("Instructor added successfully", HttpStatus.CREATED);

        } catch (IOException e) {
            return new ResponseEntity<>("Error processing image upload: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // add mapping PUT("/instructor/{id}") to update an existing Instructor
    @PutMapping("/instructor")
    public ResponseEntity<String> updateInstructor(
            @RequestParam(value = "image", required = false) MultipartFile image,
            @Valid @ModelAttribute InstructorDto instructorDto,
            BindingResult result
    ) throws IOException, GeneralSecurityException {

        Integer instructorId = instructorDto.getId();
        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        // Check if the Instructor ID is provided
        if (instructorId == null) {
            return new ResponseEntity<>("Instructor ID is required for updating", HttpStatus.BAD_REQUEST);
        }

        // Check if the Instructor exists
        Optional<Instructor> optionalInstructor = instructorDao.findById(instructorDto.getId());
        if (optionalInstructor.isEmpty()) {
            return new ResponseEntity<>("Instructor not found", HttpStatus.NOT_FOUND);
        }


        Instructor existingInstructor = optionalInstructor.get();

        // Update instructor information
        existingInstructor.setId(instructorDto.getId());
        existingInstructor.setName(instructorDto.getName());
        existingInstructor.setTotalStudents(instructorDto.getTotalStudents());
        existingInstructor.setDescription(instructorDto.getDescription());
        existingInstructor.setDesignation(instructorDto.getDesignation());
        existingInstructor.setGithubUrl(instructorDto.getGithubUrl());
        existingInstructor.setLinkedinUrl(instructorDto.getLinkedinUrl());

        // Handle image update
        if (image != null && !image.isEmpty()) {
            try {
                File tempFile = File.createTempFile("instructor", null);
                image.transferTo(tempFile);
                String imageUrl = instructorService.uploadImageToDrive(tempFile);

                if (imageUrl == null || imageUrl.isEmpty()) {
                    return new ResponseEntity<>("Failed to upload image to Google Drive", HttpStatus.INTERNAL_SERVER_ERROR);
                }

                // Update instructors image URL
                existingInstructor.setImage(imageUrl);

            } catch (IOException ex) {
                return new ResponseEntity<>("Error processing image upload: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        // Update modified timestamp
        existingInstructor.setModified(LocalDateTime.now());

        // save updated instructor
        instructorDao.save(existingInstructor);
        return new ResponseEntity<>("Instructor updated successfully", HttpStatus.OK);
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
