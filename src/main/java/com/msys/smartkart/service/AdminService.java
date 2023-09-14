package com.msys.smartkart.service;

import com.msys.smartkart.entity.Admin;

public interface AdminService {
    void addAdmin(final Admin admin);

   Admin findAdminByEmailAddressAndPassword(final String emailAddress, final String password);
}
