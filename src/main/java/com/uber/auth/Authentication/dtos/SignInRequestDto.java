package com.uber.auth.Authentication.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInRequestDto {

    @NotBlank(message = "email is required field and cannot be blank")
    private String email;

    @NotBlank(message = "password is required field and cannot be blank")
    private String password;
}
