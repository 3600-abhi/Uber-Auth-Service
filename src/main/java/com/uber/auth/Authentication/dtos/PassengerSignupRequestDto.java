package com.uber.auth.Authentication.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerSignupRequestDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
