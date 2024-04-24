package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.entity.Reviews;
import com.appxbuild.nagpurit.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
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

    @PostMapping("/reviews")
    public Reviews addCheckIn(@RequestBody Reviews reviews) {
//        courseCategories.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        reviews.setCreated(dt);
        Reviews newReviews = reviewsService.save(reviews);
        return newReviews;
    }

    @PutMapping("/reviews")
    public Reviews updateCheckIn(@RequestBody Reviews reviews) {
        Reviews existingReviews = reviewsService.findById(reviews.getId());

        if (existingReviews == null) {
            throw new RuntimeException("Login Detail with id " + reviews.getId() + " not found");
        }
        reviews.setCreated(existingReviews.getCreated());

        Reviews newReviews = reviewsService.save(reviews);

        return newReviews;
    }

    @DeleteMapping("/reviews/{id}")
    public String deleteCheckIn(@PathVariable int id) {
        Reviews reviews = reviewsService.findById(id);
        if (reviews == null) {
            throw new RuntimeException("Reviews id is not found " + id);
        }
        reviewsService.deleteById(id);
        return ("Deleted Reviews id " + id);
    }


}
