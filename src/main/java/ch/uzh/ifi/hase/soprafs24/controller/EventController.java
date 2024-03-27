package ch.uzh.ifi.hase.soprafs24.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.uzh.ifi.hase.soprafs24.rest.dto.AssignmentDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.CommentDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.EventDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.IngredientDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.RecipeDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserDTO;
import ch.uzh.ifi.hase.soprafs24.service.EventService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController {

    private final EventService eventService;

    // Get all events for a specific user
    @GetMapping
    public ResponseEntity<Set<EventDTO>> getAllEventsOfUser() {
        Set<EventDTO> usersEvents = eventService.findAllEvents();
        return new ResponseEntity<>(usersEvents, HttpStatus.OK);
    }

    // Get detailed information about a single event
    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEventInformation(@PathVariable Long eventId) {
        EventDTO eventDTO = eventService.findEventById(eventId);
        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    // Create a new event
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Delete an event
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get a list of all participants for an event
    @GetMapping("/{eventId}/participants")
    public ResponseEntity<Set<UserDTO>> getAllParticipantsOfEvent(@PathVariable Long eventId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Add a participant to an event
    @PostMapping("/{eventId}/participants")
    public ResponseEntity<UserDTO> addParticipantToEvent(@PathVariable Long eventId, @Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Remove a participant from an event
    @DeleteMapping("/{eventId}/participants/{userId}")
    public ResponseEntity<Void> removeParticipant(@PathVariable Long eventId, @PathVariable Long userId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get recipes associated with an event
    @GetMapping("/{eventId}/recipes")
    public ResponseEntity<Set<RecipeDTO>> getRecipesOfEvent(@PathVariable Long eventId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Add a recipe to an event
    @PostMapping("/{eventId}/recipes")
    public ResponseEntity<RecipeDTO> addRecipeToEvent(@PathVariable Long eventId, @Valid @RequestBody RecipeDTO recipeDTO) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Delete a wrongly added recipe from an event
    @DeleteMapping("/{eventId}/recipes/{recipeId}")
    public ResponseEntity<Void> deleteWronglyAddedRecipeFromEvent(@PathVariable Long eventId, @PathVariable Long recipeId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Retrieve comments for an event
    @GetMapping("/{eventId}/comments")
    public ResponseEntity<Set<CommentDTO>> retrieveComments(@PathVariable Long eventId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Add a comment to an event
    @PostMapping("/{eventId}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long eventId, @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Delete a comment from an event
    @DeleteMapping("/{eventId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long eventId, @PathVariable Long commentId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get ingredients for an event
    @GetMapping("/{eventId}/ingredients")
    public ResponseEntity<Set<IngredientDTO>> getIngredientsOfEvent(@PathVariable Long eventId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Add an ingredient to an event
    @PostMapping("/{eventId}/ingredients")
    public ResponseEntity<IngredientDTO> addIngredientToEvent(@PathVariable Long eventId, @Valid @RequestBody IngredientDTO ingredientDTO) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Delete an ingredient from an event
    @DeleteMapping("/{eventId}/ingredients/{ingredientId}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long eventId, @PathVariable Long ingredientId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get a list of ingredient assignments for users in an event
    @GetMapping("/{eventId}/ingredients/assignments")
    public ResponseEntity<Set<AssignmentDTO>> getListOfIngredientAssignmentsOfUsers(@PathVariable Long eventId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Get assigned ingredients for a specific user in an event
    @GetMapping("/{eventId}/ingredients/assignments/{userId}")
    public ResponseEntity<Set<IngredientDTO>> getAssignedIngredientsOfUser(@PathVariable Long eventId, @PathVariable Long userId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Assign an ingredient to a user for an event
    @PostMapping("/{eventId}/ingredients/{ingredientId}/assignments")
    public ResponseEntity<Void> assignIngredientToUser(@PathVariable Long eventId, @PathVariable Long ingredientId, @Valid @RequestBody IngredientDTO ingredientDTO) {
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Delete an assigned ingredient from a user for an event
    @DeleteMapping("/{eventId}/ingredients/{ingredientId}/assignments")
    public ResponseEntity<Void> deleteAssignedIngredientFromUser(@PathVariable Long eventId, @PathVariable Long ingredientId, @RequestParam Long userId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
