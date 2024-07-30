package com.uber.auth.Authentication.services;

import com.uber.auth.Authentication.dtos.PassengerDto;
import com.uber.auth.Authentication.dtos.PassengerSignupRequestDto;

public interface AuthService {
    public PassengerDto passengerSignup(PassengerSignupRequestDto passengerSignupRequestDto);
}
