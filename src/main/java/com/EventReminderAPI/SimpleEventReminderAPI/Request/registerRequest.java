package com.EventReminderAPI.SimpleEventReminderAPI.Request;


public class registerRequest {
    
     private String useremail;
     private String password;
     private String confirmPassword;
     private String role;

     public String getUseremail(){return useremail;}
     public String getPassword(){return password;}
     public String getConfirmPassword(){return confirmPassword;}
     public String getRole(){return role;}

    public void setUseremail(String useremail){this.useremail = useremail;}
    public void setPassword(String password){this.password = password;}
    public void setConfirmPassword(String confirmPassword){this.confirmPassword = confirmPassword;}
    public void setRole(String role){this.role = role;}

}
