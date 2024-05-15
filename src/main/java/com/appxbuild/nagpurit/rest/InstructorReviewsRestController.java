package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.InstructorReviews;
import com.appxbuild.nagpurit.entity.Reviews;
import com.appxbuild.nagpurit.service.InstructorReviewsService;
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
public class InstructorReviewsRestController {

    private InstructorReviewsService instructorReviewsService;

    @Autowired
    public InstructorReviewsRestController(InstructorReviewsService reviewsService) {
        this.instructorReviewsService = reviewsService;
    }

    @GetMapping("/instructorReviews")
    public List<InstructorReviews> findAll(){
        return instructorReviewsService.findAll();
    }

    @GetMapping("/instructorReviews/{id}")
    public InstructorReviews getReviews(@PathVariable int id){
        InstructorReviews theReviews = instructorReviewsService.findById(id);
        if (theReviews==null){
            throw new RuntimeException("Reviews id is not found " + id);
        }
        return theReviews;
    }


    @GetMapping("/instructorReviews/login/{loginId}")
    public ResponseEntity<List<InstructorReviews>> getReviewByLoginId(@PathVariable int loginId) {
        List<InstructorReviews> reviews = instructorReviewsService.findAll()
                .stream()
                .filter(u -> u.getLoginDetails() != null && u.getLoginDetails().getId() == loginId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/instructorReviews/course/{instructorId}")
    public ResponseEntity<List<InstructorReviews>> getReviewByCourseId(@PathVariable int instructorId) {
        List<InstructorReviews> reviews = instructorReviewsService.findAll()
                .stream()
                .filter(u -> u.getInstructor() != null && u.getInstructor().getId() == instructorId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviews);}

    @PostMapping("/instructorReviews")
    public InstructorReviews addReview(@RequestBody InstructorReviews reviews) {
        reviews.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        reviews.setCreated(dt);
        InstructorReviews newReviews = instructorReviewsService.save(reviews);
        return newReviews;
    }

    @PutMapping("/instructorReviews")
    public InstructorReviews updateReview(@RequestBody InstructorReviews reviews) {
        InstructorReviews existingReviews = instructorReviewsService.findById(reviews.getId());

        if (existingReviews == null) {
            throw new RuntimeException("Login Detail with id " + reviews.getId() + " not found");
        }
        reviews.setCreated(existingReviews.getCreated());

        InstructorReviews newReviews = instructorReviewsService.save(reviews);

        return newReviews;
    }

    @DeleteMapping("/instructorReviews/{id}")
    public String deleteReview(@PathVariable int id) {
        InstructorReviews reviews = instructorReviewsService.findById(id);
        if (reviews == null) {
            throw new RuntimeException("Instructor Reviews id is not found " + id);
        }
        instructorReviewsService.deleteById(id);
        return ("Deleted Reviews id " + id);
    }

}
