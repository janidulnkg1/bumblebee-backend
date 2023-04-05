package com.example.bumblebeebackend.testing;

import com.example.bumblebeebackend.controller.UserController;
import com.example.bumblebeebackend.controller.adminController;
import com.example.bumblebeebackend.exception.AdminNotFoundException;
import com.example.bumblebeebackend.exception.UserNotFoundException;
import com.example.bumblebeebackend.model.Admin;
import com.example.bumblebeebackend.model.User;
import com.example.bumblebeebackend.repository.AdminRepository;
import com.example.bumblebeebackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    UserControllerTest() {
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp()  {
        User user = new User("test@test.com", "password");
        List userList = new ArrayList<>();
        userList.add(user);
    }
    @Test
    void testSignup() {
        // Create a mock UserRepository
        UserRepository userRepository = mock(UserRepository.class);
        // Create an instance of the user controller and inject the mock repository
        UserController userController = new UserController();
        userController.setUserRepository(userRepository);

        // Create a user object to save
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("testpassword");

        // Call the signup method
        ResponseEntity<String> response = userController.signup(user);

        // Verify that the user object was saved
        verify(userRepository).save(user);

        // Verify that the response status code is 200 and the message is correct
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User created successfully", response.getBody());
    }

    @Test
    public void testLoginSuccess() {
        // Create a mock UserRepository
        UserRepository adminRepository = mock(UserRepository.class);

        // Create an instance of the user controller and inject the mock repository
        UserController userController = new UserController();
        userController.setUserRepository(userRepository);

        // Create a user object to find
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("testpassword");

        // Mock the findByemail method to return the user object
        when(userRepository.findByemail(user.getEmail())).thenReturn(user);

        // Call the login method with the correct user object
        ResponseEntity<String> response = userController.login(user);

        // Verify that the response status code is 200 and the message is correct
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Login successful", response.getBody());
    }

    @Test
    public void testLoginFailure() {
        // Create a mock UserRepository
        UserRepository userRepository = mock(UserRepository.class);

        // Create an instance of the user controller and inject the mock repository
        UserController userController = new UserController();
       userController.setUserRepository(userRepository);

        // Create an user object to find
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("testpassword");

        // Mock the findByemail method to return null
        when(userRepository.findByemail(user.getEmail())).thenReturn(null);

        // Call the login method with the incorrect user object
        ResponseEntity<String> response = userController.login(user);

        // Verify that the response status code is 401 and the message is correct
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid email or password", response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = null;
        when(userRepository.findAll()).thenReturn(userList);
        List<User> response = userController.getAllUsers();
        assertThat(response).isEqualTo(userList);
    }

    @Test
    public void testDeleteUser_Success() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);
        String response = userController.deleteUser(userId);
        assertThat(response).isEqualTo("User with id " + userId + " has been deleted!");
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteUser_Failure() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(false);
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userController.deleteUser(userId);
        });
        String expectedMessage = "Unable to find the user with id:" + userId ;
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).isEqualTo(expectedMessage);
        verify(userRepository, times(0)).deleteById(userId);
    }


}
