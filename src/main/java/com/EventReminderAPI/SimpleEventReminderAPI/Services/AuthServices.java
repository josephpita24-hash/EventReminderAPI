package com.EventReminderAPI.SimpleEventReminderAPI.Services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EventReminderAPI.SimpleEventReminderAPI.Data.TaskEvent;
import com.EventReminderAPI.SimpleEventReminderAPI.Data.userData;
import com.EventReminderAPI.SimpleEventReminderAPI.Repository.TaskEventRepository;
import com.EventReminderAPI.SimpleEventReminderAPI.Repository.userDataRepository;

@Service
public class AuthServices {

    private final userDataRepository userDataRepository;
    private final TaskEventRepository taskEventRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServices(userDataRepository userDataRepository, PasswordEncoder passwordEncoder, TaskEventRepository taskEventRepository) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
        this.taskEventRepository = taskEventRepository;
    }

    // User Registration
    public String register(String useremail, String password, String role) {
        
        // 0. Check for any null value
        if (useremail == null || password == null || role == null) {
            return "useremail,password and role are requered";
        }
        // 1. Check user if already exists
        userData user = userDataRepository.findByUseremail(useremail);
        if (user != null) {
            return "Hi " + useremail + " You already in , Please log in ";
        }

        // 2. Register user if not exist yet
        userData userData = new userData();
        userData.setUseremail(useremail);
        userData.setPassword(passwordEncoder.encode(password));
        userData.setRole(role);

        userDataRepository.save(userData);

        return "user: " + useremail + "Succsessfully Register";
    }

    // user Change Password
    public String ChangePassword(String useremail, String currentPassword, String newPassword) {

        // 0. Check for any null value
        if (useremail == null || currentPassword == null || newPassword == null) {
            return "useremail,currentPassword and newPassword are requered to change password";
        }

        // 1. Check if user exist in database
        userData user = userDataRepository.findByUseremail(useremail);
        if (user == null) {
            return "Hi " + useremail + " Does not Exist yet ! ";
        }

        // 2. Check if current password match with user password in database
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return "Hi " + useremail + " you Current Passoword does not matches ! ";
        }

        // 3. Change password
        user.setPassword(passwordEncoder.encode(newPassword));
        userDataRepository.save(user);

        return "Hi " + useremail + " You Password Changed successfully ";

    }

    // User Delete Account
    public String DeleteAccount(String useremail) {

        // 1. Check if user Exist
        userData user = userDataRepository.findByUseremail(useremail);
        if (user == null) {
            return "Hi " + useremail + " Does not Exist yet ! ";
        }

        // 2. delete Account
        userDataRepository.delete(user);

        // 3. Delete user information
       List<TaskEvent> userTasks = taskEventRepository.findByTaskOwner(useremail);
       if (userTasks == null) {
         // Do nothing !
       }else{

       taskEventRepository.deleteAll(userTasks);

       }

        return "Hi " + useremail + " Deleted  successfully ";

    }
}
