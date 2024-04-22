package com.microServices.reviewms.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean createReview(Long companyId, Review review);
    boolean deleteReviewById(Long reviewId);
    boolean updateReviewById(Long id, Review newReview);
}
