package ch.uzh.ifi.hase.soprafs24.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import ch.uzh.ifi.hase.soprafs24.entity.Comment;
import ch.uzh.ifi.hase.soprafs24.entity.Event;
import ch.uzh.ifi.hase.soprafs24.entity.Ingredient;
import ch.uzh.ifi.hase.soprafs24.entity.Recipe;
import ch.uzh.ifi.hase.soprafs24.entity.UserEntity;
import ch.uzh.ifi.hase.soprafs24.repository.EventRepository;
import ch.uzh.ifi.hase.soprafs24.repository.UserRepository;
import ch.uzh.ifi.hase.soprafs24.rest.dto.AssignmentDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.CommentDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.EventDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.IngredientDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.RecipeDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserDTO;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    private final SecurityService securityService;

    // Get all events for a specific user
    public Set<EventDTO> findAllEvents() {
        UserEntity authUser = securityService.getCurrentAuthenticatedUser();
        return authUser.getEvents().stream()
                .map(event -> DTOMapper.INSTANCE.convertEventToEventDTO(event))
                .collect(Collectors.toSet());
    }

    // Get detailed information about a single event
    public EventDTO findEventById(@NonNull Long eventId) {
        // Implementation stub
        UserEntity authUser = securityService.getCurrentAuthenticatedUser();
        // Find the event in the set
        Event event = authUser.getEvents().stream()
                .filter(e -> e.getId().equals(eventId)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found!"));
        return DTOMapper.INSTANCE.convertEventToEventDTO(event);
    }

    // Create a new event
    public EventDTO createEvent(@NonNull Event event) {
        eventRepository.save(event);
        return DTOMapper.INSTANCE.convertEventToEventDTO(event);
    }

    // Delete an event
    public void deleteEvent(@NonNull Long eventId) {
        Event event = isParticipantOfEvent(eventId);
        eventRepository.delete(event); // is that correct???
    }

    // Get a list of all participants for an event
    public Set<UserDTO> findAllParticipantsByEventId(@NonNull Long eventId) {
        Event event = isParticipantOfEvent(eventId);
        return event.getParticipants().stream()
                .map(participant -> DTOMapper.INSTANCE.convertUserEntityToUserDTO(participant))
                .collect(Collectors.toSet());
    }

    // Add a participant to an event
    public void addParticipantToEvent(@NonNull Long eventId, @NonNull UserEntity userEntity) {
        Event event = isHostOfEvent(eventId);

        // Find the user
        UserEntity userToAdd = userRepository.findById(userEntity.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
 
        // Add Participant to event
        event.getParticipants().add(userToAdd);
        eventRepository.save(event);
    }

    // Remove a participant from an event
    public void removeParticipantFromEvent(@NonNull Long eventId, @NonNull Long userId) {
        Event event = isHostOfEvent(eventId);

        // Find the user in the set
        UserEntity userToDelete = event.getParticipants().stream()
                .filter(user -> user.getId().equals(userId)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found!"));

        // Remove User from Participants
        event.getParticipants().remove(userToDelete);
        eventRepository.save(event);
    }

    // Get recipes associated with an event
    public Set<RecipeDTO> findAllRecipesByEventId(@NonNull Long eventId) {
        Event event = isParticipantOfEvent(eventId);
        return null;
    }

    // Add a recipe to an event
    public RecipeDTO addRecipeToEvent(@NonNull Long eventId, @NonNull Recipe recipe) {
        // Implementation stub
        return null;
    }

    // Delete a wrongly added recipe from an event
    public void deleteRecipeFromEvent(@NonNull Long eventId, @NonNull Long recipeId) {
        // Implementation stub
    }

    // Retrieve comments for an event
    public Set<CommentDTO> findAllCommentsByEventId(@NonNull Long eventId) {
        // Implementation stub
        return null;
    }

    // Add a comment to an event
    public CommentDTO addCommentToEvent(@NonNull Long eventId, @NonNull Comment comment) {
        // Implementation stub
        return null;
    }

    // Delete a comment from an event
    public void deleteCommentFromEvent(@NonNull Long eventId, @NonNull Long commentId) {
        // Implementation stub
    }

    // Get ingredients for an event
    public Set<IngredientDTO> findAllIngredientsByEventId(@NonNull Long eventId) {
        // Implementation stub
        return null;
    }

    // Add an ingredient to an event
    public IngredientDTO addIngredientToEvent(@NonNull Long eventId, @NonNull Ingredient ingredient) {
        // Implementation stub
        return null;
    }

    // Delete an ingredient from an event
    public void deleteIngredientFromEvent(@NonNull Long eventId, @NonNull Long ingredientId) {
        // Implementation stub
    }

    // Get a list of ingredient assignments for users in an event
    public Set<AssignmentDTO> findAllIngredientAssignmentsByEventId(@NonNull Long eventId) {
        // Implementation stub
        // isch die methode würklich nötig? wo isch de usecase? mer chönted au eifach
        // nur die undedrah also pro user????
        return null;
    }

    // Get assigned ingredients for a specific user in an event
    public Set<IngredientDTO> findAssignedIngredientsByUserIdAndEventId(@NonNull Long eventId, @NonNull Long userId) {
        // Implementation stub
        return null;
    }

    // Assign an ingredient to a user for an event
    public AssignmentDTO assignIngredientToUser(@NonNull Long eventId, @NonNull Long ingredientId,
            @NonNull UserEntity userEntity) {
        // Implementation stub
        // wemmer eppis returne? wieso ned void? controller returned void...? also da
        // doch eig au?
        return null;
    }

    // Delete an assigned ingredient from a user for an event
    public void deleteAssignedIngredientFromUser(@NonNull Long eventId, @NonNull Long ingredientId,
            @NonNull Long userId) {
        // Implementation stub
    }

    public Event isParticipantOfEvent(@NonNull Long eventId) {
        UserEntity authUser = securityService.getCurrentAuthenticatedUser();
        Event event = authUser.getEvents().stream()
                .filter(e -> e.getId().equals(eventId)).findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found or User not found!"));
        return event;
    }

    public Event isHostOfEvent(@NonNull Long eventId) {
        UserEntity authUser = securityService.getCurrentAuthenticatedUser();
        Event event = authUser.getEvents().stream()
                .filter(e -> e.getId().equals(eventId)).findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found or User not found!"));
        // how handle host request
        return event;
    }
}
