package in.krishna.expensetrackerapi.controller;

import in.krishna.expensetrackerapi.entity.User;
import in.krishna.expensetrackerapi.entity.UserModel;
import in.krishna.expensetrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("User is logged in", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.createUser(userModel), HttpStatus.CREATED);
    }
}
