package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE (:genreId IS NULL OR obj.genre.id = :genreId) ORDER BY obj.title",
    countQuery = "SELECT COUNT(obj) FROM Movie obj JOIN obj.genre WHERE (:genreId IS NULL OR obj.genre.id = :genreId) ORDER BY obj.title")
    Page<Movie> searchByGenre(Long genreId, Pageable pageable);
}
