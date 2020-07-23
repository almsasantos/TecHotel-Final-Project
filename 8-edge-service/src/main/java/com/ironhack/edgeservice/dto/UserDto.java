package com.ironhack.edgeservice.dto;

import com.ironhack.edgeservice.model.enums.Role;

/**
 * UserDto to create and get Users
 */
public class UserDto {
    /**
     * Username of User
     */
    private String username;
    /**
     * Password of User
     */
    private String password;
    /**
     * Role of User
     */
    private Role rol;

    /**
     * Full Constructor of UserDto
     * @param username Username of User
     * @param password Password of User
     * @param rol Role of User
     */
    public UserDto(String username, String password, Role rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    /**
     * Get username of User
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set Username of USer
     * @param username String username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get Password of User
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password of User
     * @param password String password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get Role of User
     * @return Role role
     */
    public Role getRol() {
        return rol;
    }

    /**
     * Set Role of User
     * @param rol Role role
     */
    public void setRol(Role rol) {
        this.rol = rol;
    }
}
