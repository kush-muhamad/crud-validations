package com.kush.valdiationDemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


import java.util.List;

@Entity

@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Please fill in the director")
    private String director;

    @NotBlank(message = "Please fill in the plot")
    private String plot;

    @Min(value = 1, message = "Movie should not be less than 1 minute")
    private int duration;

    @ManyToOne
    @JsonBackReference  // Prevents the circular reference by not serializing this side
    @JoinColumn(name = "user_id")
    private User user; // since a movie can only be associated with one user

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonManagedReference  // Prevents the reviews from showing up in the Film side
    private List<Review> reviews;

    public Film() {
    }

    public Film(Long id, String title, String director, String plot, int duration, User user, List<Review> reviews) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.plot = plot;
        this.duration = duration;
        this.user = user;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", plot='" + plot + '\'' +
                ", duration=" + duration +
                ", user=" + user +
                ", reviews=" + reviews +
                '}';
    }
}
