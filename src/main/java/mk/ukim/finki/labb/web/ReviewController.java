package mk.ukim.finki.labb.web;

import mk.ukim.finki.labb.model.Review;
import mk.ukim.finki.labb.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{housingId}")
    public ResponseEntity<Review> addReview(@PathVariable Long housingId, @RequestParam Double rating, @RequestParam String comment) {
        return ResponseEntity.ok(reviewService.addReview(housingId, rating, comment));
    }

    @GetMapping("/{housingId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long housingId) {
        return ResponseEntity.ok(reviewService.getReviewsForBooking(housingId));
    }

    @GetMapping("/{housingId}/average")
    public ResponseEntity<Double> getAverageReview(@PathVariable Long housingId) {
        return ResponseEntity.ok(reviewService.getAverageReview(housingId));
    }

}