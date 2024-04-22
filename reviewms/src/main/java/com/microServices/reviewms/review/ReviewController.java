package com.microServices.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId,@RequestBody Review review){
        boolean isReviewed = reviewService.createReview(companyId,review);
        if(isReviewed){
            return new ResponseEntity<>("Review Created",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Not created",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteById(@PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReviewById(reviewId);
        if (isDeleted) {
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{ReviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long ReviewId,@RequestBody Review newReview){
        boolean updated = reviewService.updateReviewById(ReviewId, newReview);
        if(updated){
            return new ResponseEntity<>("Request Successful", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        boolean updated = jobService.updateJobById(id, updatedJob);
//        if(updated)return new ResponseEntity<>("Job Updated",HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
