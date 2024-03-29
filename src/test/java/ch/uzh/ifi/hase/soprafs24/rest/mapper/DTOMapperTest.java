package ch.uzh.ifi.hase.soprafs24.rest.mapper;

import ch.uzh.ifi.hase.soprafs24.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DTOMapperTest
 * Tests if the mapping between the internal and the external/API representation
 * works.
 */
public class DTOMapperTest {
  // @Test
  // public void testCreateUser_fromUserPostDTO_toUser_success() {
  //   // create UserPostDTO
  //   UserPostDTO userPostDTO = new UserPostDTO();
  //   userPostDTO.setName("name");
  //   userPostDTO.setUsername("username");

  //   // MAP -> Create user
  //   UserEntity user = DTOMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);

  //   // check content
  //   assertEquals(userPostDTO.getName(), user.getName());
  //   assertEquals(userPostDTO.getUsername(), user.getUsername());
  // }

  // @Test
  // public void testGetUser_fromUser_toUserGetDTO_success() {
  //   // create User
  //   UserEntity user = new UserEntity();
  //   user.setName("Firstname Lastname");
  //   user.setUsername("firstname@lastname");
  //   user.setStatus(UserStatus.OFFLINE);
  //   user.setToken("1");

  //   // MAP -> Create UserGetDTO
  //   UserGetDTO userGetDTO = DTOMapper.INSTANCE.convertEntityToUserGetDTO(user);

  //   // check content
  //   assertEquals(user.getId(), userGetDTO.getId());
  //   assertEquals(user.getName(), userGetDTO.getName());
  //   assertEquals(user.getUsername(), userGetDTO.getUsername());
  //   assertEquals(user.getStatus(), userGetDTO.getStatus());
  // }
}
