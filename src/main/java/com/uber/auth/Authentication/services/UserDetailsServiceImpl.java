package com.uber.auth.Authentication.services;

import com.uber.auth.Authentication.helper.AuthDriverDetails;
import com.uber.auth.Authentication.helper.AuthPassengerDetails;
import com.uber.auth.Authentication.models.Driver;
import com.uber.auth.Authentication.models.Passenger;
import com.uber.auth.Authentication.repositories.DriverRepository;
import com.uber.auth.Authentication.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * This class is responsible for loading the user in the form of UserDetails object for Auth.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRepository.findPassengerByEmail(email);

        if (passenger.isPresent()) {
            return new AuthPassengerDetails(passenger.get());
        }

        Optional<Driver> driver = driverRepository.findDriverByEmail(email);

        if(driver.isPresent()) {
            return new AuthDriverDetails(driver.get());
        }

        throw new UsernameNotFoundException("No passenger or Driver found with given Email");
    }
}
