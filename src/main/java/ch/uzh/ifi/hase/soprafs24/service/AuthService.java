package ch.uzh.ifi.hase.soprafs24.service;

import java.util.Collections;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import ch.uzh.ifi.hase.soprafs24.constant.SD;
import ch.uzh.ifi.hase.soprafs24.entity.Role;
import ch.uzh.ifi.hase.soprafs24.entity.UserEntity;
import ch.uzh.ifi.hase.soprafs24.repository.RoleRepository;
import ch.uzh.ifi.hase.soprafs24.repository.UserRepository;
import ch.uzh.ifi.hase.soprafs24.rest.dto.AuthResponseDTO;
import ch.uzh.ifi.hase.soprafs24.security.JWTGenerator;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    public AuthResponseDTO login(String username, String password) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be null or empty");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new AuthResponseDTO(token);
    }

    public void register(String username, String password) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be null or empty");
        }

        if (userRepository.existsByUsername(username)) {
            throw new BadCredentialsException("Username is taken!");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));

        Role role = roleRepository.findByName(SD.Role_userRole)
                .orElseThrow(() -> new IllegalStateException("Default role not found"));
        userEntity.setRoles(Collections.singletonList(role));

        userRepository.save(userEntity);
    }
}