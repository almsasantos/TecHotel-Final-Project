package com.ironhack.securityservice.service;

import com.ironhack.securityservice.enums.Role;
import com.ironhack.securityservice.model.User;
import com.ironhack.securityservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    private List<User> users;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        User user = new User("pepe", "pepe", Role.BASIC);
        User user2 = new User("antonio", "antonio", Role.PREMIUM);
        users = Stream.of(user,user2).collect(Collectors.toList());
        userRepository.saveAll(Stream.of(user,user2).collect(Collectors.toList()));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void loadUserByUsername() {
        UserDetails customUser = userService.loadUserByUsername("pepe");
        assertEquals(1, customUser.getAuthorities().size());
        assertTrue(customUser.isAccountNonExpired());
        assertTrue(customUser.isAccountNonLocked());
        assertTrue(customUser.isCredentialsNonExpired());
        assertTrue(customUser.isEnabled());
    }

    @Test
    void loadUserByUsername_UserNotExist_Exception() {
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("ajhljkhfgasg"));
    }

    @Test
    void createUser() {
        User user = new User("manolo", "manolo", Role.PREMIUM);
        User result = userService.createUser(user);
        assertEquals("manolo", result.getUsername());
        System.out.println(result.getPassword());
        assertTrue(result.getPassword().matches("\\$\\w{2}\\$\\w{2}\\$.{53}"));
    }

    @Test
    void createUser_UserExists_ThrowException() {
        User user = new User("pepe", "pepe", Role.BASIC);
        assertNull(userService.createUser(user));

    }

    @Test
    void viewAllBasicUsers() {
        List<User> result = userService.viewAllBasicUsers();
        assertEquals(users.get(0).getUsername(), result.get(0).getUsername());

    }

    @Test
    void viewAllPremiumUsers() {
        List<User> result = userService.viewAllPremiumUsers();
        assertEquals(users.get(1).getUsername(), result.get(0).getUsername());

    }
}