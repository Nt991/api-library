package com.api.library.conrollers;

import com.api.library.entities.User;
import com.api.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<User> findUserById(@PathVariable("user-id") long userId) {
        User user= userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User savedUser=userService.saveUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<User> deleteUserById(@PathVariable("user-id") long userId) {
       userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
