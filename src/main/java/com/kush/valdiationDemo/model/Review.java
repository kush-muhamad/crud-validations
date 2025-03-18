package com.kush.valdiationDemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name = "reviews")

public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;
    @NotBlank(message = "Comment is mandatory")
    private String comment;
    @Min(value = 0 , message = "rating should be between 0 and 5")
    @Max(value = 5 , message = "rating should be between 0 and 5")
    private int rating;


    @ManyToOne
    @JoinColumn(name = "film_id")
    @JsonIgnore

    private Film film;

    public Review() {
    }

    public Review(Long id, String comment, int rating, Film film) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.film = film;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", film=" + film +
                '}';
    }
}
