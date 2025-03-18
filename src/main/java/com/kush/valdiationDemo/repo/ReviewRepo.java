package com.kush.valdiationDemo.repo;

import com.kush.valdiationDemo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review , Long> {
    List<Review> findByFilmId(Long filmId);
    //List<Review> findByUserId(Long userId);
}
