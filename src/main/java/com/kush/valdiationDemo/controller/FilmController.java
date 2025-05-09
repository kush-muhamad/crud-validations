package com.kush.valdiationDemo.controller;

import com.kush.valdiationDemo.model.Film;
import com.kush.valdiationDemo.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }



    @GetMapping("/films")
    public ResponseEntity<Map<String, Object>> getFilms(
            @RequestParam(defaultValue = "3") int limit,  // Default limit = 3
            @RequestParam(defaultValue = "0") int offset  // Default offset = 0
    ) {
        Map<String, Object> response = new HashMap<>();
        List<Film> films = filmService.getFilmsByPagination(limit, offset);

        response.put("returnCode", 200);
        response.put("returnObject", films);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<Map<String, Object>> getSingleFilm(@PathVariable Long id){
        Map<String , Object> response = new HashMap<>();
        Film film = filmService.getFilmById(id);
        response.put("returnCode", 200);
        response.put("ReturnObject", film);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/films/sorted/{field}")
    public ResponseEntity<Map<String, Object>> getSortedFilms(
            @PathVariable String field,   // The field you want to sort by (e.g., "title", "director")
            @RequestParam(defaultValue = "asc") String direction  // "asc" for ascending and "desc" for descending
    ) {
        Map<String, Object> response = new HashMap<>();
        List<Film> sortedFilms = filmService.sortFilm(field, direction);
        response.put("returnCode", 200);
        response.put("returnObject", sortedFilms);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PostMapping("/films/add/{user_id}")
    public ResponseEntity<Map<String , Object>> createFilm(@Valid @RequestBody Film film, @PathVariable Long user_id){
        Map<String , Object> response = new HashMap<>();
        Film newFilm = filmService.addFilm(film, user_id);
        response.put("returnCode", 201);
        response.put("ReturnObject", newFilm);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/film/edit/{user_id}/{film_id}")
    public ResponseEntity<Map<String , Object>> updateFilm(@PathVariable Long user_id, @PathVariable Long film_id, @Valid @RequestBody Film film){
        Map<String , Object> response = new HashMap<>();
        Film updatedFilm = filmService.updateFilm(user_id, film_id, film);
        response.put("returnCode", 200);
        response.put("ReturnObject", "Successfully Updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/film/delete/{user_id}/{film_id}")
    public ResponseEntity<Map<String,Object>> deleteFilm(@PathVariable Long user_id, @PathVariable Long film_id, @Valid @RequestBody Film film){
        Map<String , Object> response = new HashMap<>();
        Film deleteFilm = filmService.deleteFilm(user_id, film_id, film);
        response.put("returnCode", 200);
        response.put("ReturnObject", "Successfully Deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
