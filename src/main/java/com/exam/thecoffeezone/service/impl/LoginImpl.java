package com.exam.thecoffeezone.service.impl;

import com.exam.thecoffeezone.model.Admin;
import com.exam.thecoffeezone.repository.AdminRepo;
import com.exam.thecoffeezone.service.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginImpl implements Login {
    private final AdminRepo adminRepo;

    @Autowired
    public LoginImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
        }

    @Override
    public boolean authenticateUser(String email, String password) {
        // Find the user by email
        Admin user = adminRepo.findByEmail(email);

        System.out.println(user);
        if (user == null) {
            return false;
        }

        // Check if the user exists and the password is correct
        return user.getPassword().equals(password);
    }

    @Override
    public boolean registerAdmin(String fullNames, String email, String password) {
        // Check if the user with the given email already exists
        if (adminRepo.findByEmail(email) != null) {
            return false; // User already exists
        }

        // Create a new Admin instance and save it
        Admin newAdmin = new Admin();
        newAdmin.setFullNames(fullNames);
        newAdmin.setEmail(email);
        newAdmin.setPassword(password); // Store the password as plain text
        adminRepo.save(newAdmin);

        return true;
    }
}
