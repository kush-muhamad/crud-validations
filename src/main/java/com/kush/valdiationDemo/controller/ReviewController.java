package com.kush.valdiationDemo.controller;

import com.kush.valdiationDemo.model.Review;
import com.kush.valdiationDemo.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<Map<String , Object>> getReviews(){
        Map<String , Object> response = new HashMap<>();
        List <Review> reviews = reviewService.getReviews();
        response.put("returnCode", 200);
        response.put("ReturnObject", reviews);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<Map<String , Object>> getReviewById(@PathVariable Long id){
        Map<String , Object> response = new HashMap<>();
        Review review = reviewService.getReviewById(id);
        response.put("returnCode", 200);
        response.put("ReturnObject", review);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PostMapping("/review/add/{film_id}")
    public ResponseEntity<Map<String, Object>> addReview(@Valid @RequestBody Review review,

                                                         @PathVariable Long film_id) {
        Map<String, Object> response = new HashMap<>();
        Review addReview = reviewService.addReview(review,  film_id);
        response.put("returnCode", 201);
        response.put("returnObject", addReview);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/review/edit/{user_id}/{film_id}/{review_id}")
    public ResponseEntity<Map<String ,Object>> editReview(@Valid @RequestBody Review review , @PathVariable Long user_id ,@PathVariable Long film_id , @PathVariable Long review_id){
        Map<String, Object> response = new HashMap<>();
        Review editedReview = reviewService.editReview(review , user_id , film_id,review_id);
        response.put("returnCode",200);
        response.put("returnObject",editedReview);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/review/delete/{user_id}/{film_id}/{review_id}")
    public ResponseEntity<Map<String ,Object>> deleteReview(@Valid @RequestBody Review review , @PathVariable Long user_id ,@PathVariable Long film_id , @PathVariable Long review_id){
        Map<String , Object> response = new HashMap<>();
        Review deleteReview = reviewService.deleteReview(review , user_id , film_id,review_id);
        response.put("returnCode" , 200);
        response.put("returnObject", deleteReview);
        response.put("returnMessage", "successfully Deleted");

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    }
