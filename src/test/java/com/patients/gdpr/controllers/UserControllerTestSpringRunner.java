package com.patients.gdpr.controllers;

import com.patients.gdpr.dto.UserDTO;
import com.patients.gdpr.model.User;
import com.patients.gdpr.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@Deprecated
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTestSpringRunner {

    @MockBean
    UserService userService;

    @MockBean
    User user;

    @MockBean
    UserDTO userDTO;

    @Autowired
    UserController userController;

    @Test
    public void loginUserTest() {
        Mockito.when(userService.loginUser(userDTO)).thenReturn(userDTO);
        Assert.assertEquals(userDTO, userController.loginUser(userDTO));
    }

}
