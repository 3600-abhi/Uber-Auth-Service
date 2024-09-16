package com.uber.auth.Authentication.dtos;

import com.uber.auth.Authentication.models.Passenger;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerSignInResponseDto {
    private String name;
    private String email;
    private String phoneNumber;

    public static PassengerSignInResponseDto from(Passenger passenger) {

        return PassengerSignInResponseDto.builder()
                                         .name(passenger.getName())
                                         .email(passenger.getEmail())
                                         .phoneNumber(passenger.getPhoneNumber())
                                         .build();
    }
}
