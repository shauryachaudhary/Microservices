package com.microServices.reviewms.review.Impl;

import com.microServices.reviewms.review.Review;
import com.microServices.reviewms.review.ReviewRepository;
import com.microServices.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
     private ReviewRepository reviewRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
       List<Review> reviews = reviewRepository.findByCompanyId(companyId);
       return reviews;
    }



    @Override
    public boolean createReview(Long companyId,Review review) {
        if(companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }

    }
    @Override
    public boolean deleteReviewById(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review !=null && reviewRepository.existsById(reviewId)) {
            reviewRepository.delete(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateReviewById(Long id, Review newReview) {
        Review review = reviewRepository.findById(id).orElse(null);
        if(review != null) {

            review.setName(newReview.getName());
            review.setDescription(newReview.getDescription());
            review.setRating(newReview.getRating());

            reviewRepository.save(review);
            return true;
        }

        return false;
    }

}
