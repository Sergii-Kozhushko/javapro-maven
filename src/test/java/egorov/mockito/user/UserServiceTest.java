package egorov.mockito.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * egorov.mockito.user.UserServiceTest.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 18-May-2023 15:14
 */

/*Создайте класс UserService, который зависит от класса UserRepository.
        UserRepository содержит метод saveUser(User user),
        сохраняющий пользователя в базе данных. Напишите тест, используя Mockito,
        чтобы убедиться, что метод saveUser был вызван с корректным пользователем.*/

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserEntity user1;
    @Mock
    UserRepository userRepository;
    UserService userService;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository);
    }

    @Test
    public void testUserService() {
        userService.save(user1);
        verify(userRepository).saveUser(user1);
    }

    @Test
    void userByEmail() {
        userService.userByEmail("email@gmail.com");
        verify(userRepository).getUserByEmail("email@gmail.com");
    }

    @Test
    void userByEmailException() {
        UserService userService1 = new UserService(new UserRepository());
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService1.userByEmail("wrong email"));

    }
}
