package ch.uzh.ifi.hase.soprafs24.repository;

import ch.uzh.ifi.hase.soprafs24.entity.Assignment;
import ch.uzh.ifi.hase.soprafs24.entity.Event;
import ch.uzh.ifi.hase.soprafs24.entity.Ingredient;
import ch.uzh.ifi.hase.soprafs24.entity.UserEntity;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("assignmentRepository")
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    Optional<Assignment> findById(Long id);
    Set<Assignment> findByEventAndUser(Event event, UserEntity user);
    Optional<Assignment> findByEventAndUserAndIngredient(Event event, UserEntity user, Ingredient ingredient);
}