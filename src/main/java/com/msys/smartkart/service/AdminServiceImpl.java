package com.msys.smartkart.service;

import com.msys.smartkart.entity.Admin;
import com.msys.smartkart.repository.AdminRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    final AdminRepository adminRepository;

    @Override
    public void addAdmin(final Admin admin) {
        log.info("Saving admin ");
        adminRepository.save(admin);
    }

    @Override
    public Admin findAdminByEmailAddressAndPassword(final String emailAddress, final String password) {
        return adminRepository.findAdminByEmailAddressAndPassword(emailAddress,password);
    }
}
