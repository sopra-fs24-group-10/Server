package ch.uzh.ifi.hase.soprafs24.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import ch.uzh.ifi.hase.soprafs24.entity.UserEntity;
import ch.uzh.ifi.hase.soprafs24.rest.dto.AuthResponseDTO;
import ch.uzh.ifi.hase.soprafs24.security.JWTGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    public AuthResponseDTO login(String username, String password) { // Strings never null, checked in DTO layer

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO register(@NonNull UserEntity userEntity) {

        // No further validation is needed, this is done on the DTO layer
        String password = userEntity.getPassword(); // store password before hashing for subsequent login request
        userService.createUser(userEntity);
        return login(userEntity.getUsername(), password);
    }

    public void logout() {
        // TODO implement logout function of http only cookies used
        log.debug("Log out User: {}", "XY");
    }
}