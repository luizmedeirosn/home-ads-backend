package com.luizmedeirosn.homeads.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.luizmedeirosn.homeads.configs.security.JwtService;
import com.luizmedeirosn.homeads.entities.User;
import com.luizmedeirosn.homeads.entities.enums.UserRole;
import com.luizmedeirosn.homeads.repositories.UserRepository;
import com.luizmedeirosn.homeads.shared.dto.request.SigninDTO;
import com.luizmedeirosn.homeads.shared.dto.request.SignupDTO;
import com.luizmedeirosn.homeads.shared.dto.response.LoginDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public LoginDTO signup(@Valid SignupDTO signupDTO) {
        User user = User.builder()
                .username(signupDTO.username())
                .email(signupDTO.email())
                .password(passwordEncoder.encode(signupDTO.password()))
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
        return new LoginDTO(user.getId(), user.getRole(), jwtService.generateToken(user));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public LoginDTO signin(@Valid SigninDTO signinDTO) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signinDTO.email(), signinDTO.password()));

        User user = userRepository.findByEmail(signinDTO.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));

        return new LoginDTO(user.getId(), user.getRole(), jwtService.generateToken(user));
    }

}
