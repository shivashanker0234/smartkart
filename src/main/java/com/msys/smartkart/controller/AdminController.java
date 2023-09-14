package com.msys.smartkart.controller;

import com.msys.smartkart.entity.Admin;
import com.msys.smartkart.repository.AdminRepository;
import com.msys.smartkart.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class AdminController {
    final AdminService adminService;
    final AdminRepository adminRepository;

    @PostMapping("/adminRegister")
    public String addAdmin(@RequestBody Admin admin) {

        adminService.addAdmin(admin);
        log.info("Admin saved successfully");

        return "Admin added successfully";

    }

    @GetMapping("/adminLogin")
    public String adminLogin( @RequestParam String emailAddress, @RequestParam String password) throws Exception {

        Admin newAdmin = null;
        log.info("New instance of Admin class create with empty object");

        if (emailAddress != null && password != null) {
            log.info("Finding the Admin with emailAddress :" +emailAddress);
            newAdmin = adminRepository.findAdminByEmailAddressAndPassword(emailAddress, password);
            log.info("found : "+newAdmin);

        }
        if (newAdmin == null) {
            log.info("Admin with emailAddress : "+emailAddress+" Not found");
            throw new Exception("Email or/and Password incorrect");
        }
        return "Welcome Admin ";
    }
}
