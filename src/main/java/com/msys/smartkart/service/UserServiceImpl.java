package com.msys.smartkart.service;

import com.msys.smartkart.entity.User;
import com.msys.smartkart.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Override
    public void registerUser(final User user) {
//        final String encode = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encode);
        userRepository.save(user);
        log.info("User Registered Successfully");
    }

    @Override
    public boolean findUserByEmailAddress(final String emailAddress) {
        User validUser = userRepository.findUserByEmailAddress(emailAddress);
        if (validUser == null) {
            log.info(emailAddress + "is new User");
            return true;
        }
        log.warn(emailAddress + " Already you have account please login");
        return false;
    }

    @Override
    public boolean login(final User user) {
//       User findUser= findUserByEmailAddress(user.getEmailAddress());
        User isValid = userRepository.findUserByEmailAddressAndPassword(user.getEmailAddress(), user.getPassword());
//        boolean isValid=userRepository.getUserByEmailAddressAndPassword(user.getEmailAddress(), user.getPassword());
        if (isValid == null) {
            log.info("User found Login Successful");
            return true;
        }
        log.info("User not found");
        log.warn("emailAddress and/or password entered wrong");
        return false;
    }

    @Override
    public User fetchUserByEmailAndPassword(final String emailAddress, final String password) {
     return userRepository.findUserByEmailAddressAndPassword(emailAddress, password);

    }
    @Override
    public User updateUserById(final User user) {

        final User updateUser = userRepository.findById(user.getId()).get();
        log.info("fetching User by User Id : " + user.getId());
        log.info("User name before update  : " + user.getName());

        updateUser.setName(user.getName());
        log.info("Updated User Name = to " + updateUser.getName());

        updateUser.setMobileNumber(user.getMobileNumber());
        log.info("Updated User MobileNumber =  to " + user.getMobileNumber());

        updateUser.setAddress(user.getAddress());
        log.info("Updated User Address = to " + user.getAddress());

        return userRepository.save(updateUser);
    }
    @Override
    public User getUserById(final Integer userId) {
        return userRepository.findById(userId).get();
    }
}
