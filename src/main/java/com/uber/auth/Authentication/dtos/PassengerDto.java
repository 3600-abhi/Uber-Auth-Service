package com.uber.auth.Authentication.dtos;

import com.uber.auth.Authentication.models.Passenger;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;  //encrypted

    public static PassengerDto from(Passenger passenger) {

        return PassengerDto.builder()
                           .id(passenger.getId())
                           .name(passenger.getName())
                           .email(passenger.getEmail())
                           .password(passenger.getPassword())
                           .phoneNumber(passenger.getPhoneNumber())
                           .build();
    }
}
