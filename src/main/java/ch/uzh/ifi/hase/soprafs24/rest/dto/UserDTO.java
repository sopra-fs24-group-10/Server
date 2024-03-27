package ch.uzh.ifi.hase.soprafs24.rest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotNull(message = "User id is required!")
    private Long id;
    private String username;
    private String firstname;
    private String lastname;    
}
