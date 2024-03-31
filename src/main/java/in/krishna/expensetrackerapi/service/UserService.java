package in.krishna.expensetrackerapi.service;

import in.krishna.expensetrackerapi.entity.User;
import in.krishna.expensetrackerapi.entity.UserModel;

public interface UserService {
    User createUser(UserModel userModel);

    User getLoggedInUser();
}
