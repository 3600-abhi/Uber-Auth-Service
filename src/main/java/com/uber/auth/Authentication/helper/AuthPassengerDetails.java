package com.uber.auth.Authentication.helper;

import com.uber.auth.Authentication.models.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * why we need this class ??
 * Because spring security works on UserDetails polymorphic type for auth
 */
public class AuthPassengerDetails extends Passenger implements UserDetails {

    private String username; // email, userid ...etc
    private String password;

    public AuthPassengerDetails(Passenger passenger) {
        this.username = passenger.getEmail();
        this.password = passenger.getPassword();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
