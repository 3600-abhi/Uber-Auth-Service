package com.uber.auth.Authentication.dtos;

import com.uber.auth.Authentication.models.Passenger;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
public class PassengerSignupRequestDto {

    @NotBlank(message = "name is required field and cannot be blank")
    private String name;

    @NotBlank(message = "email is required field and cannot be blank")
    private String email;

    @NotBlank(message = "password is required field and cannot be blank")
    private String password;

    @NotBlank(message = "phoneNumber is required field and cannot be blank")
    private String phoneNumber;


    public static Passenger convertToPassengerModel(PassengerSignupRequestDto passengerSignupRequestDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return Passenger.builder()
                        .name(passengerSignupRequestDto.getName())
                        .email(passengerSignupRequestDto.getEmail())
                        .password(passwordEncoder.encode(passengerSignupRequestDto.getPassword()))
                        .phoneNumber(passengerSignupRequestDto.getPhoneNumber())
                        .build();
    }
}
