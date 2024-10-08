package com.uber.auth.Authentication.helper;

import com.uber.auth.Authentication.models.Driver;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthDriverDetails extends Driver implements UserDetails {

    private String username; // email, userid ...etc
    private String password;

    public AuthDriverDetails(Driver driver) {
        this.username = driver.getEmail();
        this.password = driver.getPassword();
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
