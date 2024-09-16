package com.uber.auth.Authentication.dtos;

import com.uber.auth.Authentication.models.Booking;
import com.uber.auth.Authentication.models.Car;
import com.uber.auth.Authentication.models.Driver;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverSignInResponseDto {
    private String name;
    private String email;
    private String licenseNumber;
    private String aadharCard;
    private Car car;
    private String activeCity;
    private Double rating;
    private List<Booking> bookings = new ArrayList<>();

    public static DriverSignInResponseDto from(Driver driver) {
        return DriverSignInResponseDto.builder()
                                      .name(driver.getName())
                                      .email(driver.getEmail())
                                      .licenseNumber(driver.getLicenseNumber())
                                      .aadharCard(driver.getAadharCard())
                                      .car(driver.getCar())
                                      .activeCity(driver.getActiveCity())
                                      .rating(driver.getRating())
                                      .bookings(driver.getBookings())
                                      .build();
    }
}
