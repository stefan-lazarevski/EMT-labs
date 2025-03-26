package mk.ukim.finki.labb.service.impl;

import mk.ukim.finki.labb.model.Housing;
import mk.ukim.finki.labb.model.Review;
import mk.ukim.finki.labb.repository.HousingRepository;
import mk.ukim.finki.labb.repository.ReviewRepository;
import mk.ukim.finki.labb.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final HousingRepository housingRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, HousingRepository housingRepository) {
        this.reviewRepository = reviewRepository;
        this.housingRepository = housingRepository;
    }


    @Override
    public Review addReview(Long housingId, Double rating, String comment) {
        Housing housing = housingRepository.findById(housingId)
                .orElseThrow(() -> new RuntimeException("Housing not found"));

        Review review = new Review(comment, rating, housing);
        housing.getReviews().add(review);
        housingRepository.save(housing);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsForBooking(Long bookingId) {
        return reviewRepository.findByHousingId(bookingId);
    }

    @Override
    public Double getAverageReview(Long housingId) {
        List<Review> reviews = reviewRepository.findByHousingId(housingId);
        return reviews.isEmpty() ? 0.0 : reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}