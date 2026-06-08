package com.EventReminderAPI.SimpleEventReminderAPI.Data;

/**
 * userData class represents a user entity in the SimpleWeatherAPI application.
 * This class is mapped to the "users" collection in MongoDB and handles user account information.
 * 
 * Attributes:
 * - id: Unique identifier for the user
 * - useremail: User's email address for authentication and communication
 * - password: User's encrypted password for account security
 * - role: User's role/privilege level (e.g., admin, user)
 * - registrationDate: Timestamp when the user registered in the system
 * 
 * This class provides getter and setter methods for all fields to facilitate
 * data access and manipulation within the application.
 * 
 * Note: Passwords should be stored securely using encryption/hashing techniques in a production environment.
 * Thanks for you contributions.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class userData {
     
    // Fields representing user information
     @Id
     private String id;
     private String useremail;
     private String password;
     private String role;
     private String registrationDate;

     // Default constructor for initializing a new user with the current registration date
     public userData() {

       // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        
        // Define the pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // Format the date-time into a String
        String formattedDateTime = now.format(formatter);

        // Set the registration date to the current date and time
        this.registrationDate = formattedDateTime; 
       }

     // Getter and setter
     public String getId() { return id; }
     public void setId(String id) { this.id = id; }

     public String getUseremail() { return useremail; }
     public void setUseremail(String useremail) { this.useremail = useremail; }

     public String getPassword() { return password; }
     public void setPassword(String password) { this.password = password; }

     public String getRole() { return role; }
     public void setRole(String role) { this.role = role; }

     public String getRegistrationDate() { return registrationDate; }
     public void setRegistrationDate(String registrationDate) { this.registrationDate = registrationDate; }

}
