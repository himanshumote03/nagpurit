package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.CourseRatings;
import com.appxbuild.nagpurit.entity.InstructorRatings;
import com.appxbuild.nagpurit.service.CourseRatingsService;
import com.appxbuild.nagpurit.service.InstructorRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InstructorRatingsRestController {

    private InstructorRatingsService instructorRatingsService;

    @Autowired
    public InstructorRatingsRestController(InstructorRatingsService instructorRatingsService) {
        this.instructorRatingsService = instructorRatingsService;
    }

    @GetMapping("/instructorRatings")
    public List<InstructorRatings> findAll(){
        return instructorRatingsService.findAll();
    }

    @GetMapping("/instructorRatings/{id}")
    public InstructorRatings getInstructorRatings(@PathVariable int id){
        InstructorRatings theInstructorRatings = instructorRatingsService.findById(id);
        if (theInstructorRatings==null){
            throw new RuntimeException("CourseRatings id is not found " + id);
        }
        return theInstructorRatings;
    }


    @GetMapping("/instructorRatings/login/{loginId}")
    public ResponseEntity<List<InstructorRatings>> getInstructorRatingsByLoginId(@PathVariable int loginId) {
        List<InstructorRatings> instructorRatings = instructorRatingsService.findAll()
                .stream()
                .filter(u -> u.getLoginDetails() != null && u.getLoginDetails().getId() == loginId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(instructorRatings);
    }

    @GetMapping("/instructorRatings/instructor/{instructorId}")
    public ResponseEntity<List<InstructorRatings>> getInstructorRatingsByCourseId(@PathVariable int instructorId) {
        List<InstructorRatings> instructorRatings = instructorRatingsService.findAll()
                .stream()
                .filter(u -> u.getInstructor() != null && u.getInstructor().getId() == instructorId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(instructorRatings);
    }

    @PostMapping("/instructorRatings")
    public InstructorRatings addInstructorRatings(@RequestBody InstructorRatings instructorRatings) {
        instructorRatings.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        instructorRatings.setCreated(dt);
        InstructorRatings newInstructorRatings = instructorRatingsService.save(instructorRatings);
        return newInstructorRatings;
    }

    @PutMapping("/instructorRatings")
    public InstructorRatings updateInstructorRatings(@RequestBody InstructorRatings instructorRatings) {
        InstructorRatings existingInstructorRatings = instructorRatingsService.findById(instructorRatings.getId());

        if (existingInstructorRatings == null) {
            throw new RuntimeException("Login Detail with id " + instructorRatings.getId() + " not found");
        }
        instructorRatings.setCreated(existingInstructorRatings.getCreated());

        InstructorRatings newInstructorRatings = instructorRatingsService.save(instructorRatings);

        return newInstructorRatings;
    }

    @DeleteMapping("/instructorRatings/{id}")
    public String deleteInstructorRatings(@PathVariable int id) {
        InstructorRatings instructorRatings = instructorRatingsService.findById(id);
        if (instructorRatings == null) {
            throw new RuntimeException("InstructorRatings id is not found " + id);
        }
        instructorRatingsService.deleteById(id);
        return ("Deleted InstructorRatings id " + id);
    }

}
