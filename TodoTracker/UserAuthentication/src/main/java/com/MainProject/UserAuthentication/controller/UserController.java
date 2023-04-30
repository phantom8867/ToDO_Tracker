package com.MainProject.UserAuthentication.controller;

import com.MainProject.UserAuthentication.domain.Users;
import com.MainProject.UserAuthentication.exception.UserAlreadyExistsException;
import com.MainProject.UserAuthentication.exception.UserNotFoundException;
import com.MainProject.UserAuthentication.service.SecurityTokenGenerator;
import com.MainProject.UserAuthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/todo/v1")
public class UserController {

    UserService userService;
    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody Users user) throws UserAlreadyExistsException {

        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> saveAdmin(@RequestBody Users user) throws UserAlreadyExistsException {

        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteUser(email), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody Users user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody Users user) {
        Users result = userService.loginCheck(user.getEmail(), user.getPassword());
        if(result!=null) {

            //valid user
            //generate token
            Map<String, String> map = securityTokenGenerator.tokenGenerator(result);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            //invalid user or user does not exist
            return new ResponseEntity<>("invalid user or user does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
