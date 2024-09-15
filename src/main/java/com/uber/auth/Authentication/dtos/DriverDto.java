package com.uber.auth.Authentication.dtos;

import com.uber.auth.Authentication.models.Driver;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverDto {
    private String name;
    private String email;
    private String password;
    private String drivingLicenseNo;
    private String aadharCardNo;
    String activeCity;

    public static DriverDto from(Driver driver) {
        return DriverDto.builder()
                        .name(driver.getName())
                        .email(driver.getEmail())
                        .password(driver.getPassword())
                        .aadharCardNo(driver.getAadharCard())
                        .drivingLicenseNo(driver.getLicenseNumber())
                        .activeCity(driver.getActiveCity())
                        .build();
    }
}
