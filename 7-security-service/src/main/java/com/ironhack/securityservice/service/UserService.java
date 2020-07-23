package com.ironhack.securityservice.service;

import com.ironhack.securityservice.enums.Role;
import com.ironhack.securityservice.model.User;
import com.ironhack.securityservice.repository.UserRepository;
import com.ironhack.securityservice.security.CustomSecurityUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * User service
 */
@Repository
public class UserService implements UserDetailsService, Serializable {
    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    /**
     * User Repository
     */
    @Autowired
    UserRepository userRepository;
    /**
     * Load User by username
     * @param username String username
     * @return UserDetails
     * @throws UsernameNotFoundException Username not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("[INIT]" +  username + " is trying to log in");
        User user = userRepository.findAllByUsername(username).orElseThrow(() -> {
            LOGGER.warn("[WARN]" +  username + " tried to login but got rejected");
            throw new UsernameNotFoundException("User not found");
        });
        LOGGER.info("[END]" +  username + " logged in");
        return new CustomSecurityUser(user);
    }
    /**
     * Is User a Basic User
     * @param user User user
     * @return boolean
     */
    public boolean isBasicUser(User user) {
        LOGGER.info("[INIT]" +  user.getUsername() + " checking if ROLE is BASIC");
        return user.getRol().equals(Role.BASIC);
    }

    /**
     * Is User a Premium User
     * @param user User user
     * @return boolean
     */
    public boolean isPremiumUser(User user) {
        LOGGER.info("[INIT]" +  user.getUsername() + " checking if ROLE is PREMIUM");
        return user.getRol().equals(Role.PREMIUM);
    }

    /**
     * Is User an Admin
     * @param user User user
     * @return boolean
     */
    public boolean isAdmin(User user) {
        LOGGER.info("[INIT]" +  user.getUsername() + " checking if ROLE is ADMIN");
        return user.getRol().equals(Role.ADMIN);
    }

    /**
     * Create User
     * @param user User user
     * @return User
     */
    @Transactional
    public User createUser(User user) {
        LOGGER.info("[INIT] Creating user " + user.getUsername());
        if (userRepository.findAllByUsername(user.getUsername()).isPresent()) {
            LOGGER.warn("[WARN]" +  user.getUsername() + " was already created");
            return null;
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        LOGGER.info("[END]" +  user.getUsername() + " created");
        return userRepository.save(user);
    }

    /**
     * Get all Basic Users
     * @return List of Users
     */
    public List<User> viewAllBasicUsers() {
        LOGGER.info("Showing all Basic Users");
        return userRepository.findByRolEquals(Role.BASIC);
    }

    /**
     * Get all Premium Users
     * @return List of Users
     */
    public List<User> viewAllPremiumUsers() {
        LOGGER.info("Showing all Premium Users");
        return userRepository.findByRolEquals(Role.PREMIUM);
    }

    /**
     * Create a new basic
     * @param basic User basic
     * @return User basic
     */
    public User createBasicUser(User basic) {
        basic.setRol(Role.BASIC);
        return createUser(basic);
    }

    /**
     * Create a new premium
     * @param premium User premium
     * @return User premium
     */
    public User createPremiumUser(User premium) {
        premium.setRol(Role.PREMIUM);
        return createUser(premium);
    }

    /**
     * Create a new admin
     * @param admin User admin
     * @return User admin
     */
    public User createAdminUser(User admin) {
        admin.setRol(Role.ADMIN);
        return createUser(admin);
    }

    public boolean login(User user) {
        return userRepository.findById(user.getId()).isPresent();
    }
}