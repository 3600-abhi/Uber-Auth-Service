package com.uber.auth.Authentication.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInResponseDto {
    private boolean success;
}
