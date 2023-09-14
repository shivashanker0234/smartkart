package com.msys.smartkart.repository;

import com.msys.smartkart.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findAdminByEmailAddress(String emailAddress);

    Admin findAdminByEmailAddressAndPassword(String emailAddress, String password);
}
