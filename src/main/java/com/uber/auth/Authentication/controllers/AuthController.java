package com.uber.auth.Authentication.controllers;

import com.uber.auth.Authentication.dtos.PassengerSignupRequestDto;
import com.uber.auth.Authentication.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<?> passengerSignup(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto) {
        return new ResponseEntity<>(this.authService.passengerSignup(passengerSignupRequestDto), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> passengerSignin() {
        return new ResponseEntity<>("signin successfully", HttpStatus.OK);
    }
}
