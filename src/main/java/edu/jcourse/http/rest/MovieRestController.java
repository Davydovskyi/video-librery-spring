package edu.jcourse.http.rest;

import edu.jcourse.dto.PageResponse;
import edu.jcourse.dto.movie.MovieCreateEditDto;
import edu.jcourse.dto.movie.MovieFilter;
import edu.jcourse.dto.movie.MovieReadDto;
import edu.jcourse.service.MovieService;
import edu.jcourse.validation.group.CreateAction;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static edu.jcourse.util.HttpPath.MOVIE_BY_ID;
import static edu.jcourse.util.HttpPath.REST_MOVIES;

@RestController
@RequestMapping(REST_MOVIES)
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<MovieReadDto> findAll(MovieFilter filter, Pageable pageable) {
        Page<MovieReadDto> movies = movieService.findAll(filter, pageable);
        return PageResponse.of(movies);
    }

    @GetMapping(MOVIE_BY_ID)
    public MovieReadDto findById(@PathVariable Integer id) {
        return movieService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MovieReadDto create(@Validated({Default.class, CreateAction.class}) @RequestBody MovieCreateEditDto movie) {
        return movieService.create(movie);
    }

    @PutMapping(value = MOVIE_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MovieReadDto update(@PathVariable Integer id,
                               @Validated({Default.class, CreateAction.class}) @RequestBody MovieCreateEditDto movie) {
        return movieService.update(id, movie)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(MOVIE_BY_ID)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return movieService.delete(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}