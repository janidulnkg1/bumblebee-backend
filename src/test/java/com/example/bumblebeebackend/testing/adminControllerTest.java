package com.example.bumblebeebackend.testing;

import com.example.bumblebeebackend.exception.AdminNotFoundException;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


import com.example.bumblebeebackend.model.Admin;
import com.example.bumblebeebackend.repository.AdminRepository;
import com.example.bumblebeebackend.controller.adminController;




@ExtendWith(MockitoExtension.class)
 class adminControllerTest {

     @InjectMocks
     private adminController adminController;

     @Mock
     private AdminRepository adminRepository;

     adminControllerTest() {
     }

     @org.junit.jupiter.api.BeforeEach
     void setUp()  {
         Admin admin = new Admin("test@test.com", "password");
         List adminList = new ArrayList<>();
         adminList.add(admin);
    }


    @Test
     void testSignup()  {

        // Create a mock AdminRepository
        AdminRepository adminRepository = mock(AdminRepository.class);
        // Create an instance of the admin controller and inject the mock repository
        adminController adminController = new adminController();
        adminController.setAdminRepository(adminRepository);

        // Create an admin object to save
        Admin admin = new Admin();
        admin.setEmail("test@example.com");
        admin.setPassword("testpassword");

        // Call the signup method
        ResponseEntity<String> response = adminController.signup(admin);

        // Verify that the admin object was saved
        verify(adminRepository).save(admin);

        // Verify that the response status code is 200 and the message is correct
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("ADMIN ACCOUNT created successfully", response.getBody());
    }


     @Test
     public void testLoginSuccess() {
         // Create a mock AdminRepository
         AdminRepository adminRepository = mock(AdminRepository.class);

         // Create an instance of the admin controller and inject the mock repository
         adminController adminController = new adminController();
         adminController.setAdminRepository(adminRepository);

         // Create an admin object to find
         Admin admin = new Admin();
         admin.setEmail("test@example.com");
         admin.setPassword("testpassword");

         // Mock the findByemail method to return the admin object
         when(adminRepository.findByemail(admin.getEmail())).thenReturn(admin);

         // Call the login method with the correct admin object
         ResponseEntity<String> response = adminController.login(admin);

         // Verify that the response status code is 200 and the message is correct
         assertEquals(200, response.getStatusCodeValue());
         assertEquals("Login successful", response.getBody());
     }

     @Test
     public void testLoginFailure() {
         // Create a mock AdminRepository
         AdminRepository adminRepository = mock(AdminRepository.class);

         // Create an instance of the admin controller and inject the mock repository
         adminController adminController = new adminController();
         adminController.setAdminRepository(adminRepository);

         // Create an admin object to find
         Admin admin = new Admin();
         admin.setEmail("test@example.com");
         admin.setPassword("testpassword");

         // Mock the findByemail method to return null
         when(adminRepository.findByemail(admin.getEmail())).thenReturn(null);

         // Call the login method with the incorrect admin object
         ResponseEntity<String> response = adminController.login(admin);

         // Verify that the response status code is 401 and the message is correct
         assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
         assertEquals("Invalid email or password", response.getBody());
     }

     @Test
     public void testGetAllAdmins() {
         List<Admin> adminList = null;
         when(adminRepository.findAll()).thenReturn(adminList);
         List<Admin> response = adminController.getAllAdmins();
         assertThat(response).isEqualTo(adminList);
     }

     @Test
     public void testDeleteAdmin_Success() {
         Long adminId = 1L;
         when(adminRepository.existsById(adminId)).thenReturn(true);
         String response = adminController.deleteAdmin(adminId);
         assertThat(response).isEqualTo("Admin with id " + adminId + " has been deleted!");
         verify(adminRepository, times(1)).deleteById(adminId);
     }

     @Test
     public void testDeleteAdmin_Failure() {
         Long adminId = 1L;
         when(adminRepository.existsById(adminId)).thenReturn(false);
         AdminNotFoundException exception = assertThrows(AdminNotFoundException.class, () -> {
             adminController.deleteAdmin(adminId);
         });
         String expectedMessage = "Unable to find the admin with id:" + adminId ;
         String actualMessage = exception.getMessage();
         assertThat(actualMessage).isEqualTo(expectedMessage);
         verify(adminRepository, times(0)).deleteById(adminId);
     }
}