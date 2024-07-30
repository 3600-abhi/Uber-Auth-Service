package com.uber.auth.Authentication.services;

import com.uber.auth.Authentication.dtos.PassengerDto;
import com.uber.auth.Authentication.dtos.PassengerSignupRequestDto;
import com.uber.auth.Authentication.models.Passenger;
import com.uber.auth.Authentication.repositories.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final PassengerRepository passengerRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(PassengerRepository passengerRepository, PasswordEncoder passwordEncoder) {
        this.passengerRepository = passengerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PassengerDto passengerSignup(PassengerSignupRequestDto passengerSignupRequestDto) {
        Passenger passenger = Passenger.builder()
                                       .name(passengerSignupRequestDto.getName())
                                       .email(passengerSignupRequestDto.getEmail())
                                       .password(passwordEncoder.encode(passengerSignupRequestDto.getPassword()))
                                       .phoneNumber(passengerSignupRequestDto.getPhoneNumber())
                                       .build();

        Passenger createdPassenger = this.passengerRepository.save(passenger);

        PassengerDto response = PassengerDto.from(createdPassenger);

        return response;
    }

}
