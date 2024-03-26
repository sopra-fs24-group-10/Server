package ch.uzh.ifi.hase.soprafs24.service;

import ch.uzh.ifi.hase.soprafs24.constant.SD;
import ch.uzh.ifi.hase.soprafs24.entity.Recipe;
import ch.uzh.ifi.hase.soprafs24.entity.Role;
import ch.uzh.ifi.hase.soprafs24.entity.UserSetting;
import ch.uzh.ifi.hase.soprafs24.entity.UserEntity;
import ch.uzh.ifi.hase.soprafs24.repository.RoleRepository;
import ch.uzh.ifi.hase.soprafs24.repository.UserRepository;
import ch.uzh.ifi.hase.soprafs24.rest.dto.FavouriteDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserSettingDTO;
import ch.uzh.ifi.hase.soprafs24.rest.mapper.DTOMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    
    private final SecurityService securityService;

    public void createUser(@NonNull UserEntity userEntity) {

        if (userRepository.existsByUsername(userEntity.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is taken!");
        }

        // Hash the password to store it safely
        userEntity.setPassword(securityService.hashPassword(userEntity.getPassword()));

        // Assign default Role
        Role role = roleRepository.findByName(SD.UserRole.USER)
                .orElseThrow(() -> new IllegalStateException("Default role not found"));
        userEntity.setRoles(Collections.singleton(role));

        log.debug("Created Information for User: {}", userEntity);
        userRepository.save(userEntity); // creates the user;
    }

    // This function retrieves the profile for a given user ID
    public UserDTO getUserProfile() {
        UserEntity userEntity = securityService.getCurrentAuthenticatedUser();
        return DTOMapper.INSTANCE.convertUserEntityToUserDTO(userEntity);
    }

    // This function updates the profile for a given user ID
    public void updateUserProfile(@NonNull UserEntity userEntity) {

        // Set password if a new one was provided
        if (StringUtils.isBlank(userEntity.getPassword())) {

            // if no password is provided, use existing hash
            UserEntity authUser = securityService.getCurrentAuthenticatedUser();
            userEntity.setPassword(authUser.getPassword());

        } else {
            userEntity.setPassword(securityService.hashPassword(userEntity.getPassword()));
        }
        // Update User
        userRepository.save(userEntity);
    }

    // This function retrieves the user settings for a given user ID
    public UserSettingDTO getUserSettings() {
        UserSetting userSetting = securityService.getCurrentAuthenticatedUser().getSetting();
        return DTOMapper.INSTANCE.convertUserSettingToUserSettingDTO(userSetting);
    }

    // This function updates the user settings for a given user ID
    public void updateUserSettings(@NonNull UserSetting userSetting) {
        // Stub implementation
    }

    // This function retrieves the favourite recipes for a given user ID
    public Set<FavouriteDTO> getFavouriteRecipes() {
        UserEntity authUser = securityService.getCurrentAuthenticatedUser();
        return authUser.getRecipes().stream()
                .map(recipe -> DTOMapper.INSTANCE.convertRecipeToFavouriteDTO(recipe))
                .collect(Collectors.toSet());
    }

    // This function adds a favourite recipe for a given user ID
    public Set<FavouriteDTO> addRecipeToFavourites(@NonNull Recipe recipe) {
        UserEntity authUser = securityService.getCurrentAuthenticatedUser();
        authUser.getRecipes().add(recipe); // use getter, do not override Set with setter!
        return authUser.getRecipes().stream()
                .map(r -> DTOMapper.INSTANCE.convertRecipeToFavouriteDTO(r))
                .collect(Collectors.toSet());
    }

    // This function deletes a favourite recipe for a given user ID
    public void deleteFavourite(@NonNull Long recipeId) {

        UserEntity authUser = securityService.getCurrentAuthenticatedUser();
        // Find the recipe in the set
        Recipe recipeToRemove = authUser.getRecipes().stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst().orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Favourite not found!"));

        // Remove Recipe from Favourites
        authUser.getRecipes().remove(recipeToRemove);
        userRepository.save(authUser);
    }
}
