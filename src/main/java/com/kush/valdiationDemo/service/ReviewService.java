package com.kush.valdiationDemo.service;

import com.kush.valdiationDemo.exceptions.NotFoundException;
import com.kush.valdiationDemo.model.Film;
import com.kush.valdiationDemo.model.Review;
import com.kush.valdiationDemo.model.User;
import com.kush.valdiationDemo.repo.FilmRepo;
import com.kush.valdiationDemo.repo.ReviewRepo;
import com.kush.valdiationDemo.repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final FilmRepo filmRepo;
    private final UserRepo userRepo;
    private final ReviewRepo reviewRepo;


    public ReviewService(FilmRepo filmRepo, UserRepo userRepo, ReviewRepo reviewRepo) {
        this.filmRepo = filmRepo;
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;
    }

    public List<Review> getReviews() {
        return reviewRepo.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found with id: "));
    }

    public Review addReview(@Valid Review review,  Long filmId) {
        Film film = filmRepo.findById(filmId)
                .orElseThrow(() -> new NotFoundException("Film not found with id: " + filmId));

        // Set user reference on Film
        review.setFilm(film);


        return reviewRepo.save(review);

    }

    public Review editReview(@Valid Review review, Long userId, Long filmId, Long reviewId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        // Step 2: Check if the film exists
        Film film = filmRepo.findById(filmId)
                .orElseThrow(() -> new NotFoundException("Film not found with id: " + filmId));

        // Step 3: Check if the review exists
        Review existingReview = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review not found with id: " + reviewId));

        // Step 4: Check if the review belongs to the specified user and film

        if (!existingReview.getFilm().getId().equals(filmId)) {
            throw new IllegalArgumentException("You cannot edit a review for a film that does not match.");
        }
        existingReview.setComment(review.getComment());
        existingReview.setRating(review.getRating());

        return reviewRepo.save(existingReview);
    }

    public Review deleteReview(@Valid Review review, Long userId, Long filmId, Long reviewId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        // Step 2: Check if the film exists
        Film film = filmRepo.findById(filmId)
                .orElseThrow(() -> new NotFoundException("Film not found with id: " + filmId));

        // Step 3: Check if the review exists
        Review existingReview = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review not found with id: " + reviewId));

        // Step 4: Check if the review belongs to the specified user and film

        if (!existingReview.getFilm().getId().equals(filmId)) {
            throw new IllegalArgumentException("You cannot edit a review for a film that does not match.");
        }
        reviewRepo.delete(existingReview);
        return existingReview;
    }
}
