package ch.uzh.ifi.hase.soprafs24.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ch.uzh.ifi.hase.soprafs24.rest.dto.AuthResponseDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.LoginDTO;
import ch.uzh.ifi.hase.soprafs24.rest.dto.RegisterDTO;
import ch.uzh.ifi.hase.soprafs24.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {

        AuthResponseDTO authResponseDTO = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {

        authService.register(registerDTO.getUsername(), registerDTO.getPassword());
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}