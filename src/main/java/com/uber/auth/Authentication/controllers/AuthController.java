package com.uber.auth.Authentication.controllers;

import com.uber.auth.Authentication.dtos.AuthRequestDto;
import com.uber.auth.Authentication.dtos.AuthResponseDto;
import com.uber.auth.Authentication.dtos.PassengerSignupRequestDto;
import com.uber.auth.Authentication.services.AuthService;
import com.uber.auth.Authentication.services.JwtService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Environment environment;

    public AuthController(AuthService authService, JwtService jwtService, AuthenticationManager authenticationManager, Environment environment) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.environment = environment;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<?> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto) {
        return new ResponseEntity<>(this.authService.passengerSignup(passengerSignupRequestDto), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDto authRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));

            if (authentication.isAuthenticated()) {
                String jwtToken = jwtService.createToken(authRequestDto.getEmail());

                ResponseCookie cookie = ResponseCookie.from("JwtToken", jwtToken)
                                                      .httpOnly(true)
                                                      .secure(false)
                                                      .path("/")
                                                      .maxAge(Long.parseLong(environment.getProperty("custom.cookie.expiry", "3600")))
                                                      .build();

                HttpHeaders headers = new HttpHeaders();
                headers.set(HttpHeaders.SET_COOKIE, cookie.toString());

                AuthResponseDto authResponseDto = AuthResponseDto.builder()
                                                                 .success(true)
                                                                 .build();

                return new ResponseEntity<>(authResponseDto, headers, HttpStatus.OK);
            }

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Authentication Fail", HttpStatus.UNAUTHORIZED);
    }
}
