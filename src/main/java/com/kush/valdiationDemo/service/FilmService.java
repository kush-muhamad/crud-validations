package com.kush.valdiationDemo.service;

import com.kush.valdiationDemo.exceptions.NotFoundException;
import com.kush.valdiationDemo.model.Film;
import com.kush.valdiationDemo.model.User;
import com.kush.valdiationDemo.repo.FilmRepo;
import com.kush.valdiationDemo.repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    public final FilmRepo filmRepo;

    @Autowired
    private UserRepo userRepo;

    public FilmService(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    public List<Film>  getFilms() {
       return filmRepo.findAll();
    }
    public List<Film> getFilmsByPagination(int limit , int offset){
        return filmRepo.findFilmsWithPagination(limit,offset);

    }

    public List<Film> sortFilm(String field  , String direction){
        try {
            Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            return filmRepo.findAll(Sort.by(sortDirection, field));
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage()) ;
        }

    }

    public Film getFilmById(Long id) {
        return filmRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Film not found with id: " + id));
    }

    public Film addFilm(@Valid Film film, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        film.setUser(user);  // Set user reference on Film
        user.getFilms().add(film);  // Ensure User has reference to Film

        return filmRepo.save(film);  // Save the Film
    }



    public Film updateFilm(Long userId, Long filmId, @Valid Film film) {
        //check if the user exists
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        Film existingFilm = filmRepo.findById(filmId)
                .orElseThrow(() -> new NotFoundException("Film not found with id: " + filmId));

        //does the movie belong to the user
        if (!existingFilm.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You do not have permission to update this film");
        }
        existingFilm.setTitle(film.getTitle());
        existingFilm.setDirector(film.getDirector());
        existingFilm.setPlot(film.getPlot());
        existingFilm.setDuration(film.getDuration());

    return filmRepo.save(existingFilm);



    }


    public Film deleteFilm(Long userId, Long filmId, @Valid Film film) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        Film existingFilm = filmRepo.findById(filmId)
                .orElseThrow(() -> new NotFoundException("Film not found with id: " + filmId));

        if (!existingFilm.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You do not have permission to delete this film");

        }
        filmRepo.delete(existingFilm);
        return existingFilm;

    }
}



