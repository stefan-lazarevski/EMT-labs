package mk.ukim.finki.labb.service;

import mk.ukim.finki.labb.model.Review;

import java.util.List;


public interface ReviewService {

        Review addReview(Long housingId, Double rating, String comment);

        List<Review> getReviewsForBooking(Long housingId);

        Double getAverageReview(Long housingId);
}
