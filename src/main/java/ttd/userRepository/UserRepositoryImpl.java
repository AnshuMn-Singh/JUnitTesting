package ttd.userRepository;

import ttd.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean save(User user) {
        Map<String, User> usersData = new HashMap<>();

        boolean returnValue = false;

        if(!usersData.containsKey(user.getId())){
            usersData.put(user.getId(), user);
            returnValue = true;
        }

        return returnValue;
    }
}
