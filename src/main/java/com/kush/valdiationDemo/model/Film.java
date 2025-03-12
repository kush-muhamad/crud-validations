package com.kush.valdiationDemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user; // since a movie can only be associated with one user
}
