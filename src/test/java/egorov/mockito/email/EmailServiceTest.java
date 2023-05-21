package egorov.mockito.email;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/*Создайте класс EmailService, который зависит от класса EmailClient.
        EmailClient предоставляет метод sendEmail(String recipient,
        String message), отправляющий электронную почту.
        Напишите тест, используя Mockito, чтобы убедиться,
        что метод sendEmail был вызван с правильными аргументами и обработан корректно.*/
class EmailServiceTest {

    @Test
    void sendEmailTestFunctionRun(){
        EmailClient emailClient = Mockito.mock(EmailClient.class);
        EmailService emailService = new EmailService(emailClient);
        emailService.sendEmail("user@gmail.com", "Email text");
        Mockito.verify(emailClient).sendEmail("user@gmail.com", "Email text");
    }

    @Test
    public void sendEmailExceptionTest(){
        EmailService exceptionEmailService = new EmailService(new EmailClient());
        Throwable thrown = Assertions.assertThrows(IllegalArgumentException.class,
                ()->exceptionEmailService.sendEmail("some wrong email", "message text"));
        Assertions.assertNotNull(thrown.getMessage());
        Assertions.assertEquals("Email is incorrect", thrown.getMessage());

    }
}