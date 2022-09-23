package com.tsystems.mms.demoapp.user;

import com.tsystems.mms.demoapp.domain.User;
import com.tsystems.mms.demoapp.domain.enums.Gender;
import com.tsystems.mms.demoapp.repository.UnitRepository;
import com.tsystems.mms.demoapp.repository.UserRepository;
import com.tsystems.mms.demoapp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceTest {

  @MockBean
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private UnitRepository unitRepository;


  private UserService userService;
  private List<User> testUsers;

  @BeforeEach
  public void setup() {
    userService = new UserService(userRepository,passwordEncoder, unitRepository);
    testUsers = new ArrayList<>();
    testUsers.add(createUser(1L,"test1.user@foo.bar"));
    testUsers.add(createUser(2L,"test2.user@foo.bar"));
    testUsers.add(createUser(3L,"test3.user@foo.bar"));
  }

  @Test
  void testGetAll () {

    // Mock
    Mockito.when(userRepository.findAll()).thenReturn(testUsers);

    // Assert
    List<User> users = userService.getAll();
    Assertions.assertNotNull(users);
    Assertions.assertEquals(3, users.size());
    Assertions.assertEquals(testUsers.get(0), users.get(0));

    // Verify
    Mockito.verify(userRepository, Mockito.atMostOnce()).findAll();

  }

  private User createUser(Long id, String email) {
    User user = new User();
    user.setId(id);
    user.setEmail(email);
    return user;
  }

  @Test
  void TestIfEmailValid(){
    User firstUser = new User(1L, "David", "Katona", "katonapontdavid@gmail.com", Gender.MALE, "passwords" );
    User secondUser = new User(2L, "David", "Katona", "katonapon23tdavid@gmail.com", Gender.MALE, "passwords");
    User thirdUser = new User(3L, "David", "Katona", "katonapontdavidgmailcom", Gender.MALE, "passwords");
    User fourthUser = new User(1L, "David", "Katona", "katonapontdavidgmail.com", Gender.MALE, "passwords");

    Assertions.assertTrue(userService.checkIfEmailValid(firstUser.getEmail()));
    Assertions.assertTrue(userService.checkIfEmailValid(secondUser.getEmail()));
    Assertions.assertFalse(userService.checkIfEmailValid(thirdUser.getEmail()));
    Assertions.assertFalse(userService.checkIfEmailValid(fourthUser.getEmail()));

  }
}
