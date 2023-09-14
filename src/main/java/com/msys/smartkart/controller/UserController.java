package com.msys.smartkart.controller;

import com.msys.smartkart.config.GenericResponseDto;
import com.msys.smartkart.entity.User;
import com.msys.smartkart.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
public class UserController {
    final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponseDto> addUser(final @RequestBody User user) throws Exception {

        final boolean isNotExists = userService.findUserByEmailAddress(user.getEmailAddress());

        if (isNotExists) {
            userService.registerUser(user);
            log.info("User saved successfully");

        } else {
            log.warn("User is Already exists");
            return new ResponseEntity<>(new GenericResponseDto(404, user.getEmailAddress()+
                    " This user is already registered please login "), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new GenericResponseDto(200, user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/login")
    public ResponseEntity<GenericResponseDto> login(final @RequestParam String emailAddress,
                                                    final @RequestParam String password) throws Exception {

        log.info(emailAddress);
        log.info(password);
        User newUser = null;
        if (emailAddress != null && password != null) {
            newUser = userService.fetchUserByEmailAndPassword(emailAddress, password);
            log.info("welcome : " + newUser);
        }
        if (newUser == null) {
//          throw new Exception("User Not found");
            return new ResponseEntity<>(new GenericResponseDto<>(404, "Email or/and password wrong"), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new GenericResponseDto(200, newUser), HttpStatus.ACCEPTED);
    }

//    @PutMapping("/updateUse")
//    public User updateUser(@RequestBody final User user) {
//        userService.updateUserById(user);
//        return user;
//    }

    @PutMapping("/updateUser")
    public ResponseEntity<GenericResponseDto> updateUser(@RequestBody final User user){

//        User userById = userService.getUserById(user.getId());
        userService.updateUserById(user);
        return new ResponseEntity<>(new GenericResponseDto(200,user),HttpStatus.ACCEPTED);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<GenericResponseDto> getUserById(@RequestParam final Integer userId){
        final User userById = userService.getUserById(userId);
        return new ResponseEntity<>(new GenericResponseDto<>(200,userById),HttpStatus.ACCEPTED);
    }
}
