package com.msys.smartkart.service;

import com.msys.smartkart.entity.User;

public interface UserService {
    void registerUser(final User user);

    boolean findUserByEmailAddress(final String emailAddress);

    boolean login(final User user);

    User fetchUserByEmailAndPassword(final String emailAddress, final String password);

    User updateUserById(final User user);

    User getUserById(final Integer userId);
}
