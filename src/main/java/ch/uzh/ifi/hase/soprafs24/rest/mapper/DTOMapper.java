package ch.uzh.ifi.hase.soprafs24.rest.mapper;

import ch.uzh.ifi.hase.soprafs24.entity.Event;
import ch.uzh.ifi.hase.soprafs24.entity.Recipe;
import ch.uzh.ifi.hase.soprafs24.entity.UserEntity;
import ch.uzh.ifi.hase.soprafs24.entity.UserSetting;
import ch.uzh.ifi.hase.soprafs24.rest.dto.EventDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.FavouriteDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.RegisterDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.UserSettingDTO;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * DTOMapper
 * This class is responsible for generating classes that will automatically
 * transform/map the internal representation
 * of an entity (e.g., the User) to the external/API representation (e.g.,
 * UserGetDTO for getting, UserPostDTO for creating)
 * and vice versa.
 * Additional mappers can be defined for new entities.
 * Always created one mapper for getting information (GET) and one mapper for
 * creating information (POST).
 */
@Mapper
public interface DTOMapper {

  DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

  @Mapping(source = "username", target = "username")
  @Mapping(source = "firstname", target = "firstname")
  @Mapping(source = "lastname", target = "lastname")
  @Mapping(source = "password", target = "password")
  UserEntity convertRegisterDTOtoUserEntity(RegisterDTO registerDTO);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "username", target = "username")
  @Mapping(source = "firstname", target = "firstname")
  @Mapping(source = "lastname", target = "lastname")
  UserDTO convertUserEntityToUserDTO(UserEntity userEntity);

  @Mapping(source = "design", target = "design")
  @Mapping(source = "view", target = "view")
  UserSettingDTO convertUserSettingToUserSettingDTO(UserSetting userSetting);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "api_id", target = "api_id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "description", target = "description")
  @Mapping(source = "image", target = "image")
  FavouriteDTO convertRecipeToFavouriteDTO(Recipe recipe);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "location", target = "location")
  @Mapping(source = "description", target = "description")
  @Mapping(source = "createddate", target = "createddate")
  EventDTO convertEventToEventDTO(Event event);
}
