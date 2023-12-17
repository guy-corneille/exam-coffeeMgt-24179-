package com.exam.thecoffeezone.controller;

import com.exam.thecoffeezone.model.Admin;
import com.exam.thecoffeezone.service.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final Login login;

    @Autowired
    public LoginController(Login login) {
        this.login = login;
    }

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "Login";  // Assuming the login page is named "login.html"
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@ModelAttribute("admin") Admin admin, Model model) {
        String email = admin.getEmail();
        String password = admin.getPassword();

        // Authenticate the user
        boolean isAuthenticated = login.authenticateUser(email, password);
        System.out.println(isAuthenticated);
        if (isAuthenticated) {
            // Redirect to the admin page
            return "redirect:/admin";
        } else {
            // Handle failed authentication
            model.addAttribute("error", "Invalid credentials");
            return "Login";  // Redirect back to the login page
        }
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "register";
    }

    @PostMapping("/register")
    public String registerAdmin(@ModelAttribute("admin") Admin admin, Model model) {
        String fullNames = admin.getFullNames();  // Updated method name to getFullNames
        String email = admin.getEmail();
        String password = admin.getPassword();

        // Register the admin
        boolean isRegistered = login.registerAdmin(fullNames, email, password);

        if (isRegistered) {
            // Redirect to the login page with a success message
            model.addAttribute("successMessage", "Admin registered successfully. You can now log in.");
            return "redirect:/login";
        } else {
            // Handle registration failure
            model.addAttribute("error", "Registration failed. Admin with the given email already exists.");
            return "register";
        }
    }

}
