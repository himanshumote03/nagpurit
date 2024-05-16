package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.Reviews;
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
public class ReviewsRestController {

    private ReviewsService reviewsService;

    @Autowired
    public ReviewsRestController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("/reviews")
    public List<Reviews> findAll(){
        return reviewsService.findAll();
    }

    @GetMapping("/reviews/{id}")
    public Reviews getReviews(@PathVariable int id){
        Reviews theReviews = reviewsService.findById(id);
        if (theReviews==null){
            throw new RuntimeException("Reviews id is not found " + id);
        }
        return theReviews;
    }


//    @GetMapping("/reviews/login/{loginId}")
//    public ResponseEntity<List<Reviews>> getReviewByLoginId(@PathVariable int loginId) {
//        List<Reviews> reviews = reviewsService.findAll()
//                .stream()
//                .filter(u -> u.getLoginDetails() != null && u.getLoginDetails().getId() == loginId)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(reviews);
//    }

//    @GetMapping("/reviews/course/{courseId}")
//    public ResponseEntity<List<Reviews>> getReviewByCourseId(@PathVariable int courseId) {
//        List<Reviews> reviews = reviewsService.findAll()
//                .stream()
//                .filter(u -> u.getCourses() != null && u.getCourses().getId() == courseId)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(reviews);}

    @PostMapping("/reviews")
    public Reviews addReview(@RequestBody Reviews reviews) {
        reviews.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        reviews.setCreated(dt);
        Reviews newReviews = reviewsService.save(reviews);
        return newReviews;
    }

    @PutMapping("/reviews")
    public Reviews updateReview(@RequestBody Reviews reviews) {
        Reviews existingReviews = reviewsService.findById(reviews.getId());

        if (existingReviews == null) {
            throw new RuntimeException("Login Detail with id " + reviews.getId() + " not found");
        }
        reviews.setCreated(existingReviews.getCreated());

        Reviews newReviews = reviewsService.save(reviews);

        return newReviews;
    }

    @DeleteMapping("/reviews/{id}")
    public String deleteReview(@PathVariable int id) {
        Reviews reviews = reviewsService.findById(id);
        if (reviews == null) {
            throw new RuntimeException("Reviews id is not found " + id);
        }
        reviewsService.deleteById(id);
        return ("Deleted Reviews id " + id);
    }

}
