package ttd.service;

import ttd.model.User;
import ttd.userRepository.UserRepository;
import ttd.userRepository.UserRepositoryImpl;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    EmailVerificationService emailVerificationService;
    public UserServiceImpl(UserRepository userRepository,
                           EmailVerificationService emailVerificationService) {
        this.userRepository = userRepository;
        this.emailVerificationService = emailVerificationService;
    }


    @Override
    public User createUser(
            String firstName,
            String lastName,
            String email,
            String password,
            String repeatPassword
    ) {

        if(firstName == null || firstName.trim().length() == 0){
            throw new IllegalArgumentException("User's firstname can't be null");
        }

        if(lastName == null || lastName.trim().length() == 0) {
            throw new IllegalArgumentException("User's last name is empty");
        }

        User user = new User(firstName, lastName, email, UUID.randomUUID().toString());

        // this is a one way of doing these
        // but it will not make any differrence or we are not using mocking in it
        // because we are creating instance of class as object.
//        UserRepository userRepository = new UserRepositoryImpl();
//        userRepository.save(user);

        // For mocking

        try{
            userRepository.save(user);
        }catch (RuntimeException ex){
            throw new UserServiceException("User were not created");
        }


        try{
            emailVerificationService.scheduleEmailConformation(user);
        }catch (RuntimeException ex){
            throw new UserServiceException(ex.getMessage());
        }

        return user;
    }
}
