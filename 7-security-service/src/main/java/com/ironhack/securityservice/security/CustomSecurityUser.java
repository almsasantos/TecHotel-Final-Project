package com.ironhack.securityservice.security;

import com.ironhack.securityservice.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Custom Security User
 */
public class CustomSecurityUser extends User implements UserDetails, Serializable {
    /**
     * Constructor
     * @param user User user
     */
    public CustomSecurityUser(User user) {
        System.out.println(user);
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setRol(user.getRol());
    }

    /**
     * Get all Authorities
     * @return Collection of Authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(this.getRol().toString()));
        return authorities;
    }

    /**
     * Is Account non expired
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Is Account non locked
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Is credentials non expired
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Is enables
     * @return true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}