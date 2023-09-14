package com.msys.smartkart.repository;

import com.msys.smartkart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByEmailAddress(final String emailAddress);

    User findUserByEmailAddressAndPassword(final String emailAddress,final String password);

    boolean getUserByEmailAddressAndPassword(final String emailAddress,final String password);
}
