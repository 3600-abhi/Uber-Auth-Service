package com.uber.auth.Authentication.dtos;

import com.uber.auth.Authentication.enums.CarType;
import com.uber.auth.Authentication.models.Car;
import com.uber.auth.Authentication.models.Color;
import com.uber.auth.Authentication.models.Driver;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverSignupRequestDto {

    @NotBlank(message = "name is required field and cannot be blank")
    private String name;

    @NotBlank(message = "email is required field and cannot be blank")
    private String email;

    @NotBlank(message = "password is required field and cannot be blank")
    private String password;

    @NotBlank(message = "phoneNumber is required field and cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "drivingLicenseNo is required field and cannot be blank")
    private String drivingLicenseNo;

    @NotBlank(message = "aadharCardNo is required field and cannot be blank")
    private String aadharCardNo;

    @NotBlank(message = "plateNo is required field and cannot be blank")
    private String plateNo;

    @NotBlank(message = "activeCity is required field and cannot be blank")
    private String activeCity;

    private CarType carType;

    @NotBlank(message = "carBrandName is required field and cannot be blank")
    private String carBrandName;

    @NotBlank(message = "carModel is required field and cannot be blank")
    private String carModel;

    @NotBlank(message = "carColor is required field and cannot be blank")
    private String carColor;

    public static Driver convertToDriverModel(DriverSignupRequestDto driverSignupRequestDto) {
        Color color = Color.builder()
                           .name(driverSignupRequestDto.getCarColor())
                           .build();

        Car car = Car.builder()
                     .plateNumber(driverSignupRequestDto.getPlateNo())
                     .brand(driverSignupRequestDto.getCarBrandName())
                     .carType(driverSignupRequestDto.getCarType())
                     .color(color)
                     .build();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return Driver.builder()
                     .name(driverSignupRequestDto.getName())
                     .email(driverSignupRequestDto.getEmail())
                     .password(passwordEncoder.encode(driverSignupRequestDto.getPassword()))
                     .activeCity(driverSignupRequestDto.getActiveCity())
                     .aadharCard(driverSignupRequestDto.getAadharCardNo())
                     .licenseNumber(driverSignupRequestDto.getDrivingLicenseNo())
                     .car(car)
                     .build();
    }
}
