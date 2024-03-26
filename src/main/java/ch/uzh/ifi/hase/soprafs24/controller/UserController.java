package ch.uzh.ifi.hase.soprafs24.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.uzh.ifi.hase.soprafs24.rest.dto.FavouriteDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.RecipeDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserSettingDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserUpdateDTO;
import ch.uzh.ifi.hase.soprafs24.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> getUserProfile() {
        UserDTO userDTO = userService.getUserProfile();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateUserProfile(@RequestBody UserUpdateDTO userUpdateDTO) {

        // Stub implementation
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/settings")
    public ResponseEntity<UserSettingDTO> getUserSettings() {
        // Stub implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/settings")
    public ResponseEntity<Void> updateUserSettings(@RequestBody UserSettingDTO userSettingsDTO) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/favourites")
    public ResponseEntity<Set<FavouriteDTO>> getFavouriteRecipes() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/favourites")
    public ResponseEntity<FavouriteDTO> addRecipeToFavourites(@RequestBody RecipeDTO recipeDTO) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/favourites/{recipeId}")
    public ResponseEntity<Void> deleteFavourite(@PathVariable Long recipeId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
