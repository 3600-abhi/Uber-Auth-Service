package com.uber.auth.Authentication.services;

import com.uber.auth.Authentication.dtos.*;
import com.uber.auth.Authentication.enums.AppConstant;
import com.uber.auth.Authentication.exception.AppException;
import com.uber.auth.Authentication.models.Driver;
import com.uber.auth.Authentication.models.Passenger;
import com.uber.auth.Authentication.repositories.DriverRepository;
import com.uber.auth.Authentication.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PassengerDto passengerSignup(PassengerSignupRequestDto passengerSignupRequestDto) {
        Passenger passenger = PassengerSignupRequestDto.convertToPassengerModel(passengerSignupRequestDto);
        Passenger createdPassenger = passengerRepository.save(passenger);
        return PassengerDto.from(createdPassenger);
    }

    @Override
    public ResponseEntity<PassengerSignInResponseDto> passengerSignIn(SignInRequestDto signInRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(), signInRequestDto.getPassword()));

            if (authentication.isAuthenticated()) {
                String jwtToken = jwtService.createToken(signInRequestDto.getEmail());

                ResponseCookie cookie = ResponseCookie.from(AppConstant.JWT_TOKEN_CONST, jwtToken)
                                                      .httpOnly(true)
                                                      .secure(false)
                                                      .path("/")
                                                      .maxAge(7 * 24 * 3600)
                                                      .build();

                HttpHeaders headers = new HttpHeaders();
                headers.set(HttpHeaders.SET_COOKIE, cookie.toString());

                Optional<Passenger> passenger = passengerRepository.findPassengerByEmail(signInRequestDto.getEmail());

                PassengerSignInResponseDto passengerSignInResponseDto = PassengerSignInResponseDto.from(passenger.get());

                return new ResponseEntity<>(passengerSignInResponseDto, headers, HttpStatus.OK);
            }

        } catch (BadCredentialsException e) {
            throw new AppException("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }

        throw new AppException("Authentication Fail", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public DriverDto driverSignup(DriverSignupRequestDto driverSignupRequestDto) {
        Driver driver = DriverSignupRequestDto.convertToDriverModel(driverSignupRequestDto);
        Driver createdDriver = driverRepository.save(driver);
        return DriverDto.from(createdDriver);
    }

    @Override
    public ResponseEntity<DriverSignInResponseDto> driverSignIn(SignInRequestDto signInRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(), signInRequestDto.getPassword()));

            if (authentication.isAuthenticated()) {
                String jwtToken = jwtService.createToken(signInRequestDto.getEmail());

                ResponseCookie cookie = ResponseCookie.from("JwtToken", jwtToken)
                                                      .httpOnly(true)
                                                      .secure(false)
                                                      .path("/")
                                                      .maxAge(7 * 24 * 3600)
                                                      .build();

                HttpHeaders headers = new HttpHeaders();
                headers.set(HttpHeaders.SET_COOKIE, cookie.toString());

                Optional<Driver> driver = driverRepository.findDriverByEmail(signInRequestDto.getEmail());

                DriverSignInResponseDto driverSignInResponseDto = DriverSignInResponseDto.from(driver.get());

                return new ResponseEntity<>(driverSignInResponseDto, headers, HttpStatus.OK);
            }

        } catch (BadCredentialsException e) {
            throw new AppException("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }

        throw new AppException("Authentication Failed", HttpStatus.UNAUTHORIZED);

    }


}
