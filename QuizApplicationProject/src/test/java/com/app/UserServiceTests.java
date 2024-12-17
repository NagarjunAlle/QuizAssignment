package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.app.repository.UserRepository;
import com.app.service.UserService;
import com.app.model.User;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() {
        User user = new User(null, "testuser", "test@example.com", "password");
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User created = userService.registerUser(user);
        assertNotNull(created);
        assertEquals("testuser", created.getUsername());
    }

    @Test
    public void testFetchUserByUsername() {
        User user = new User(1L, "testuser", "test@example.com", "password");
        Mockito.when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<User> found = userService.fetchUserByUsername("testuser");
        assertTrue(found.isPresent());
        assertEquals("testuser", found.get(). getUsername());
    }
}