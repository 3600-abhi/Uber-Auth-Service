package com.uber.auth.Authentication.controllers;

import com.uber.auth.Authentication.dtos.*;
import com.uber.auth.Authentication.enums.AppConstant;
import com.uber.auth.Authentication.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(AppConstant.PASSENGER_SIGNUP)
    public ResponseEntity<?> passengerSignup(@RequestBody @Valid PassengerSignupRequestDto passengerSignupRequestDto) {
        return new ResponseEntity<>(authService.passengerSignup(passengerSignupRequestDto), HttpStatus.BAD_REQUEST);
    }

    @PostMapping(AppConstant.PASSENGER_SIGNIN)
    public ResponseEntity<PassengerSignInResponseDto> passengerSignIn(@RequestBody @Valid SignInRequestDto signInRequestDto) {
        return authService.passengerSignIn(signInRequestDto);
    }

    @PostMapping(AppConstant.DRIVER_SIGNUP)
    public ResponseEntity<DriverDto> driverSignup(@RequestBody @Valid DriverSignupRequestDto driverSignupRequestDto) {
        return new ResponseEntity<>(authService.driverSignup(driverSignupRequestDto), HttpStatus.CREATED);
    }

    @PostMapping(AppConstant.DRIVER_SIGNIN)
    public ResponseEntity<DriverSignInResponseDto> driverSignIn(@RequestBody SignInRequestDto signInRequestDto) {
        return authService.driverSignIn(signInRequestDto);
    }

}
