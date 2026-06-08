package com.EventReminderAPI.SimpleEventReminderAPI.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EventReminderAPI.SimpleEventReminderAPI.Request.ChangePasswordRequest;
import com.EventReminderAPI.SimpleEventReminderAPI.Request.registerRequest;
import com.EventReminderAPI.SimpleEventReminderAPI.Services.AuthServices;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    // Services
    private final AuthServices authServices;
   
    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }

    // Register endpoint
    @PostMapping("/register")
    public String register(@RequestBody registerRequest request) {
        try {
            // 0. Check if password and confirm password are match
            if (!request.getPassword().equals(request.getConfirmPassword())) {
                return "Password and Confirm Password do not match";
            }

            // 1. Call register service
            String response = authServices.register(request.getUseremail(), request.getPassword(), request.getRole());
            return response;
        } catch (Exception e) {
            return "An error occurred during registration: " + e.getMessage();
        }

    }

    // Change Password endpoint
    @PostMapping("/change-password")
    public String changePassword(@RequestBody ChangePasswordRequest request,  Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "User is not authenticated. Please log in to change your password.";
        }

        try {
            // 1. Call change password service
            String response = authServices.ChangePassword(authentication.getName(), request.getCurrentPassword(), request.getNewPassword());
            return response;
        } catch (Exception e) {
            return "An error occurred during password change: " + e.getMessage();
        }
    }

    // Test endpoint to check if user is authenticated
    @GetMapping("/test-auth")
    public String testAuth(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "User is not authenticated.";
        }
        return "User is authenticated.";
    }

    // Delete Account endpoint
    @GetMapping("/delete-account")
    public String deleteAccount(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "User is not authenticated. Please log in to delete your account.";
        }
        // Implementation for deleting account
        try {
        String response = authServices.DeleteAccount(authentication.getName());
        return response;
        } catch (Exception e) {
            return "An error occurred while deleting the account." + e.getMessage();
        }
        
    }

}
