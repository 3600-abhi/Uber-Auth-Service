package com.uber.auth.Authentication.dtos;

import com.uber.auth.Authentication.enums.UserType;
import com.uber.auth.Authentication.models.Passenger;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerSignInResponseDto {
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String userType;

    public static PassengerSignInResponseDto from(Passenger passenger) {

        return PassengerSignInResponseDto.builder()
                                         .userId(passenger.getId())
                                         .userType(String.valueOf(UserType.PASSENGER))
                                         .name(passenger.getName())
                                         .email(passenger.getEmail())
                                         .phoneNumber(passenger.getPhoneNumber())
                                         .build();
    }
}
