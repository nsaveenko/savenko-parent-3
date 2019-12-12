package com.netcracker.savenko.fapi.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.savenko.fapi.models.StatusUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private final Integer id;
    private final String username;
    private final String flname;
    private final String password;
    private final StatusUser statusUserByIdStatus;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Integer id, String username, String flname, String password, StatusUser statusUserByIdStatus, boolean enabled,
                   Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.flname = flname;
        this.password = password;
        this.statusUserByIdStatus = statusUserByIdStatus;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
