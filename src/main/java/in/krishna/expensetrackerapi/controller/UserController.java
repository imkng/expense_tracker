package in.krishna.expensetrackerapi.controller;

import in.krishna.expensetrackerapi.entity.User;
import in.krishna.expensetrackerapi.entity.UserModel;
import in.krishna.expensetrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id){
        return new ResponseEntity<User>(userService.readUser(id), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public  ResponseEntity<User> updateUser(@RequestBody UserModel userModel, @PathVariable Long id){
        return new ResponseEntity<User>(userService.updateUser(userModel, id), HttpStatus.CREATED);
    }
}
