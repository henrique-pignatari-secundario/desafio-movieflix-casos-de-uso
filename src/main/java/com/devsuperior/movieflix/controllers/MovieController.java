package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id){
        MovieDetailsDTO dto = movieService.findById(id);

        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    @GetMapping
    public ResponseEntity<Page<MovieCardDTO>> findAll(@RequestParam(name = "genreId", required = false) Long genreId, Pageable pageable){
        Page<MovieCardDTO> page = movieService.findAll(genreId, pageable);

        return ResponseEntity.ok(page);
    }
}
