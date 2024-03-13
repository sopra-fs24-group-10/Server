package ch.uzh.ifi.hase.soprafs24.rest.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}