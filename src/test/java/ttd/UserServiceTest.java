package ttd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ttd.model.User;
import ttd.service.EmailNotificationException;
import ttd.service.EmailVerificationService;
import ttd.service.EmailVerificationServiceImpl;
import ttd.service.UserService;
import ttd.service.UserServiceException;
import ttd.service.UserServiceImpl;
import ttd.userRepository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;

    @Mock
    EmailVerificationServiceImpl emailVerificationService;

    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    @BeforeEach
    void init(){
        firstName = "Anshu";
        lastName = "Singh";
        email = "anshu@singh.com";
        password = "123456";
        repeatPassword = "123456";
    }

    @Test
    void createUser_WhenDetailProvided(){
        // mocking
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);

        User user = userServiceImpl.createUser(firstName, lastName, email, password, repeatPassword);

        assertNotNull(user, "ajcnasj");
        assertEquals(firstName, user.getFirstName(), "ad");
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertNotNull(user.getId());

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    void TestUser_WhenForNullOrEmptyValue(){
        firstName = "";
        String expectedExceptionMessage = "User's firstname can't be null";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                        userServiceImpl.createUser(firstName, lastName, email, password, repeatPassword),
                () -> expectedExceptionMessage);

        assertEquals(expectedExceptionMessage, thrown.getMessage(), () -> "error massage is not same");
    }

    @DisplayName("Empty last name causes correct exception")
    @Test
    void testCreateUser_whenLastNameIsEmpty_throwsIllegalArgumentException() {
        String lastName = "";
        String expectedExceptionMessage = "User's last name is empty";

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, ()-> {
                    userServiceImpl.createUser(firstName, lastName, email, password, repeatPassword); },
                () -> "Empty last name should have caused an Illegal Argument Exception");

        assertEquals(expectedExceptionMessage,thrown.getMessage(),
                    () -> "Exception error message is not correct");
    }

    @DisplayName("if save method cause runtimeexception then we throw customexception")
    @Test
    void testCreateUser_CustomException(){
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(RuntimeException.class);

        assertThrows(UserServiceException.class, () -> {
            userServiceImpl.createUser(firstName, lastName, email, password, repeatPassword);
        }, () -> "should throw custom exception");

    }

    @DisplayName("email exception throwns")
    @Test
    void createUser_WhenEmailThrowException(){
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);

        // it is shwing return type error bcasue this method is of void these
        //Mockito.when(emailVerificationService.scheduleEmailConformation(Mockito.any(User.class))).thenThrow();

        // so for these, we need to do these
        Mockito.doThrow(EmailNotificationException.class)
                .when(emailVerificationService)
                .scheduleEmailConformation(Mockito.any(User.class));

        // doNorthing doesnt throw any exception
        Mockito.doNothing().when(emailVerificationService)
                .scheduleEmailConformation(Mockito.any(User.class));

        assertThrows(UserServiceException.class, () -> {
            userServiceImpl.createUser(firstName, lastName, email, password, repeatPassword);
        }, () -> "throw email exception");
    }


    @DisplayName("scheduled email confirmation is excuted")
    @Test
    void createUser_ScheduledEmailConfirmation(){
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);

        Mockito.doCallRealMethod().when(emailVerificationService)
                .scheduleEmailConformation(Mockito.any(User.class));


        userServiceImpl.createUser(firstName, lastName, email, password, repeatPassword);
    }
}
