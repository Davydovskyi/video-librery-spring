package edu.jcourse.database.repository;

import edu.jcourse.database.entity.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer>,
        QuerydslPredicateExecutor<Person> {

    @EntityGraph(attributePaths = {
            "moviePersons",
            "moviePersons.movie"})
    @Override
    Optional<Person> findById(Integer id);
}