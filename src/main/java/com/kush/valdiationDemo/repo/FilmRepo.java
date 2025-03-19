package com.kush.valdiationDemo.repo;

import com.kush.valdiationDemo.model.Film;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long> {

    @Query(value = "SELECT * FROM films ORDER BY film_id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Film> findFilmsWithPagination(@Param("limit") int limit, @Param("offset") int offset);

}
