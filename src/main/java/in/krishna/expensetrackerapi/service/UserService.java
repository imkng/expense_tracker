package in.krishna.expensetrackerapi.service;

import in.krishna.expensetrackerapi.entity.User;
import in.krishna.expensetrackerapi.entity.UserModel;

public interface UserService {
    User createUser(UserModel userModel);

    User getLoggedInUser();

    User findByEmail(String email);
    User readUser(Long id);

    User updateUser(UserModel userModel, Long id);

    User updateUser(User user);
}
