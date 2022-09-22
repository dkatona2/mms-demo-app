package com.tsystems.mms.demoapp.user;

import com.tsystems.mms.demoapp.domain.User;
import com.tsystems.mms.demoapp.repository.UserRepository;
import com.tsystems.mms.demoapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class UserServiceTestWithDB {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private User user;

    @MockBean
    private UserRepository userRepositoryMock;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepositoryMock, passwordEncoder);
        user = new User();
        user.setId(1L);
        user.setEmail("katonapontdavid@gmail.com");
        user.setPassword("asd123");
    }

    @Test
    void testGetAll() {
        // Mock
//        Mockito.when(userRepositoryMock.findAll().
    }
}
