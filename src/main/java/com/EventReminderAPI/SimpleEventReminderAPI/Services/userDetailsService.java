package com.EventReminderAPI.SimpleEventReminderAPI.Services;

import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean; 

import com.EventReminderAPI.SimpleEventReminderAPI.Data.userData;
import com.EventReminderAPI.SimpleEventReminderAPI.Repository.userDataRepository;

@Service 
public class userDetailsService implements UserDetailsService { 

    @Autowired 
    private final userDataRepository userDataRepository; // repository for user data access
    
    public userDetailsService(userDataRepository userDataRepository) { 
        this.userDataRepository = userDataRepository; 
    }

    @Override // 3.  override method from UserDetailsService
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 4. load user details by username
       
        userData user = userDataRepository.findByUseremail(username); 
        if (user == null) { // check if user was found
            throw new UsernameNotFoundException("User name not Found!"); // 5. throw exception when no user matches
        }

         UserDetails userDetails = User // 6. build Spring Security UserDetails object
            .withUsername(user.getUseremail()) // 7. set username using user email
            .password(user.getPassword()) // 8. set password using stored password
            .roles(user.getRole()) // 9. set roles from user role
            .build(); // 10. create UserDetails instance

        return userDetails; // 11. return the created user details object
        
    }

    // 12. define a bean for password encoding using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
    }
    
}
