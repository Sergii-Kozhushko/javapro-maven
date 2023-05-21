package egorov.mockito.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
/*
Создайте класс AuthenticationService, который зависит от класса
        UserRepository. UserRepository содержит метод getUserById(String userId),
        возвращающий объект User по идентификатору пользователя.
        Напишите тест, используя Mockito, чтобы убедиться,
        что метод getUserById был вызван с правильным идентификатором пользователя.
*/

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    UserRepository userRepository;
    private AuthenticationService authenticationService;

    @BeforeEach
    void init() {
        authenticationService = new AuthenticationService(userRepository);
    }

    @Test
    void userRepository_getUserById() {
        //UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.doReturn(new UserEntity("name", 25)).when(userRepository).getUserById(Mockito.anyString());

        authenticationService.checkUser("67");
        Mockito.verify(userRepository).getUserById("67");
    }

    @Test
    void userRepository_getUserByIdExceptions() {
        UserRepository userRepository1 = new UserRepository();
        AuthenticationService authenticationService1 = new AuthenticationService(userRepository1);
        Throwable thrown = Assertions.assertThrows(RuntimeException.class,
                () -> authenticationService1.checkUser(null));

    }
}