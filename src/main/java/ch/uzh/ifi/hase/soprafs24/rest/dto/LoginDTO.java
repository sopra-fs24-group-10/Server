package ch.uzh.ifi.hase.soprafs24.rest.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank(message = "Username is required")
    //@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") //Dont provide hins on implementation
    private String username;

    @NotBlank(message = "Password is required")
    //@Size(min = 5, message = "Password must be at least 5 characters long") //Dont provide hins on implementation
    private String password;
}