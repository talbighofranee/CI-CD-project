package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.User;
import com.example.sprinprojet.entity.UserRole;
import com.example.sprinprojet.services.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserRepositoryTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCountUsersByRole() {
        UserRole role = UserRole.ADMIN;
        Long expectedCount = 5L;
        when(userRepository.countUsersByRole(role)).thenReturn(expectedCount);

        Long result = userRepository.countUsersByRole(role);

        assertEquals(expectedCount, result);
    }

    @Test
    void testFindByEmail() {
        String email = "test@example.com";
        User expectedUser = new User();
        expectedUser.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(expectedUser);

        User result = userRepository.findByEmail(email);

        assertEquals(expectedUser, result);
    }
}