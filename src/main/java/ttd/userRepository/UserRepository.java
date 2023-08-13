package ttd.userRepository;

import ttd.model.User;

public interface UserRepository {
    boolean save(User user);
}
