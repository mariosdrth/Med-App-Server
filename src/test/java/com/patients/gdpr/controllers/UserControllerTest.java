package com.patients.gdpr.controllers;

import com.patients.gdpr.dto.UserDTO;
import com.patients.gdpr.model.User;
import com.patients.gdpr.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @Mock
    User user;

    @Mock
    UserDTO userDTO;

    @InjectMocks
    UserController userController;

    @Test
    public void loginUserTest() {
        Mockito.when(userService.loginUser(userDTO)).thenReturn(userDTO);
        Assert.assertEquals(userDTO, userController.loginUser(userDTO));
    }

}
