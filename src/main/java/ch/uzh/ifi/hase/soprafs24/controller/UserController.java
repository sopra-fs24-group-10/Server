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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.uzh.ifi.hase.soprafs24.entity.Recipe;
import ch.uzh.ifi.hase.soprafs24.entity.UserEntity;
import ch.uzh.ifi.hase.soprafs24.entity.UserSetting;
import ch.uzh.ifi.hase.soprafs24.rest.dto.RecipeDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserSettingDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserUpdateDTO;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
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
    public ResponseEntity<Void> updateUserProfile(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        // convert updateDTO into userEntity and updates the userProfile in userService
        UserEntity userEntity = DTOMapper.INSTANCE.convertUserUpdateDTOToUserEntity(userUpdateDTO);
        userService.updateUserProfile(userEntity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/settings")
    public ResponseEntity<UserSettingDTO> getUserSettings() {
        // requests for user setting and sends them back
        UserSettingDTO userSettingDTO = userService.getUserSettings();
        return new ResponseEntity<>(userSettingDTO, HttpStatus.OK);
    }

    @PutMapping("/settings")
    public ResponseEntity<Void> updateUserSettings(@Valid @RequestBody UserSettingDTO userSettingsDTO) {
        // converst dto and sends setting to updateUserSettings
        UserSetting userSetting = DTOMapper.INSTANCE.convertUserSettingDTOToUserSetting(userSettingsDTO);
        userService.updateUserSettings(userSetting);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/favourites")
    public ResponseEntity<Set<RecipeDTO>> getFavouriteRecipes() {
        Set<RecipeDTO> favouriteDTOs = userService.getFavouriteRecipes();
        return new ResponseEntity<>(favouriteDTOs, HttpStatus.OK);
    }

    @PostMapping("/favourites")
    public ResponseEntity<Void> addRecipeToFavourites(@Valid @RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = DTOMapper.INSTANCE.convertRecipeDTOToRecipe(recipeDTO);
        userService.addRecipeToFavourites(recipe);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/favourites/{recipeId}")
    public ResponseEntity<Void> deleteFavourite(@PathVariable Long recipeId) {
        userService.deleteFavourite(recipeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
