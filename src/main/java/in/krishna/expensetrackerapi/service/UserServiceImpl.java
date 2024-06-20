package in.krishna.expensetrackerapi.service;

import in.krishna.expensetrackerapi.entity.User;
import in.krishna.expensetrackerapi.entity.UserModel;
import in.krishna.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel) {
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        //encode password before saving it to db
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User Not found for the email: " + email)
        );
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User Not found for the email: " + email));
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found for id: " + id));
    }

    @Override
    public User updateUser(UserModel userModel, Long id) {
        User existingUser = readUser(id);
        existingUser.setName(userModel.getName()!= null ? userModel.getName() : existingUser.getName());
        existingUser.setEmail(userModel.getEmail()!= null ? userModel.getEmail() : existingUser.getEmail());
        existingUser.setPassword(userModel.getPassword()!= null ? userModel.getPassword() : existingUser.getPassword());
        existingUser.setBudget(existingUser.getBudget().add(userModel.getBudget()));
        return userRepository.save(existingUser);
    }

    @Override
    public User updateUser(User user){
        return userRepository.save(user);
    }
}
