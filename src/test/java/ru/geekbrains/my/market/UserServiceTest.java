package ru.geekbrains.my.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.my.market.model.User;
import ru.geekbrains.my.market.repositories.UserRepository;
import ru.geekbrains.my.market.services.UserService;

import java.util.Optional;

@SpringBootTest (classes = UserService.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findOneUserTest() {
        User userFromDb = new User();
        userFromDb.setUsername("admin");
        userFromDb.setEmail("john_johnson@gmail.com");

        Mockito.doReturn(Optional.of(userFromDb))
                .when(userRepository)
                .findByUsername("admin");

        User userAdmin = userService.findByUsername("admin").get();
        Assertions.assertNotNull(userAdmin);
        Assertions.assertEquals("john_johnson@gmail.com", userAdmin.getEmail());
        Mockito.verify(userRepository, Mockito.times(1))
                .findByUsername(ArgumentMatchers.eq("admin"));
    }
}
