package com.uber.auth.Authentication.services;

import com.uber.auth.Authentication.dtos.*;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public PassengerDto passengerSignup(PassengerSignupRequestDto passengerSignupRequestDto);

    public ResponseEntity<SignInResponseDto> passengerSignIn(SignInRequestDto signInRequestDto);

    public DriverDto driverSignup(DriverSignupRequestDto driverSignupRequestDto);

    public ResponseEntity<SignInResponseDto> driverSignIn(SignInRequestDto signInRequestDto);
}
