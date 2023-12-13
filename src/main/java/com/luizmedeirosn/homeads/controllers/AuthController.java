package com.luizmedeirosn.homeads.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmedeirosn.homeads.services.AuthService;
import com.luizmedeirosn.homeads.shared.dto.request.SigninDTO;
import com.luizmedeirosn.homeads.shared.dto.request.SignupDTO;
import com.luizmedeirosn.homeads.shared.dto.response.LoginDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/signup")
    public ResponseEntity<LoginDTO> signup(@RequestBody @Valid SignupDTO signupDTO) {
        return ResponseEntity.ok(authService.signup(signupDTO));
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<LoginDTO> signin(@RequestBody @Valid SigninDTO signinDTO) {
        return ResponseEntity.ok(authService.signin(signinDTO));
    }

}
