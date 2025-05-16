package com.example.demo.service;



import com.example.demo.service.UserService;
import com.example.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User(1L, "John", "Doe");
    }

    @Test
    public void testAddUser() throws Exception {
        // Arrange: mock the service method to avoid calling the real method
        doNothing().when(userService).addUser(anyString(), anyString());

        // Act & Assert: Perform the HTTP request and check the result
        mockMvc.perform(post("/users")
                        .param("name", user.getName())
                        .param("surname", user.getSurname()))
                .andExpect(status().isOk())
                .andExpect(content().string(user.getName() + " added"));

        // Verify that the service method was called
        verify(userService, times(1)).addUser(user.getName(), user.getSurname());
    }

    @Test
    public void testGetUser() throws Exception {
        // Arrange: mock the service method to return a user
        when(userService.getUser(anyLong())).thenReturn(user);

        // Act & Assert: Perform the HTTP request and check the result
        mockMvc.perform(get("/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello " + user.getName()));

        // Verify that the service method was called
        verify(userService, times(1)).getUser(user.getId());
    }

    @Test
    public void testRemoveUser() throws Exception {
        // Arrange: mock the service method to avoid actual deletion
        doNothing().when(userService).removeUser(anyLong());

        // Act & Assert: Perform the HTTP request and check the result
        mockMvc.perform(delete("/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("User removed"));

        // Verify that the service method was called
        verify(userService, times(1)).removeUser(user.getId());
    }
}
