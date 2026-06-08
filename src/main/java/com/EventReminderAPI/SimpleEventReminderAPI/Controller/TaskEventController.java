package com.EventReminderAPI.SimpleEventReminderAPI.Controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EventReminderAPI.SimpleEventReminderAPI.Data.TaskEvent;
import com.EventReminderAPI.SimpleEventReminderAPI.Request.saveTaskRequest;
import com.EventReminderAPI.SimpleEventReminderAPI.Request.updateTask;
import com.EventReminderAPI.SimpleEventReminderAPI.Services.taskEventServices;
import com.EventReminderAPI.SimpleEventReminderAPI.Services.userDetailsService;

@RestController
@RequestMapping("/tasks")
public class TaskEventController {

  private taskEventServices Taskservices;
  private userDetailsService Userservice;
 
  public TaskEventController(taskEventServices taskservices, userDetailsService userservice) {
    Taskservices = taskservices;
    Userservice = userservice;
  }

   // save task
  @PostMapping("/saveTask")
  public String savetask(@RequestBody saveTaskRequest Request, Authentication authentication) {

    if (authentication == null || !authentication.isAuthenticated()) {
      return "User is not authenticated. Please log in to change your password.";
    }

    try {
      String response = Taskservices.saveTaskRequest(authentication.getName(), Request.getTitle(),
          Request.getDescription(), Request.getCategory(), Request.getTime());
      return response;

    } catch (Exception e) {
      e.printStackTrace();
      return "Something went wrong during saving..  " + e.getMessage();
    }

  }

  // updatetask
  @PostMapping("/updateTask")
  public String upddate(@RequestBody updateTask update, Authentication authentication) {

    if (authentication == null || !authentication.isAuthenticated()) {
      return "User is not authenticated. Please log in to change your password.";
    }

    try {
      String response = Taskservices.updateTask(authentication.getName(), update.getOldtitle(), update.getNewtitle(),
          update.getNewdescription(), update.getNewcategory(), update.getNewstatus(), update.getNewTime());
      return response;

    } catch (Exception e) {
      e.printStackTrace();
      return "Something went wrong during saving..  " + e.getMessage();

    }

  }

  // delete task
  @PostMapping("/deleteTask")
  public String deleteTask( @RequestParam("oldtitle") String oldtitle ,  Authentication authentication){
         
    if (authentication == null || !authentication.isAuthenticated()) {
      return "User is not authenticated. Please log in to change your password.";
    }

    try {
      String response = Taskservices.deleteTask(authentication.getName(), oldtitle);
      return response;
    } catch (Exception e) {
     e.printStackTrace();
      return "Something went wrong during saving..  " + e.getMessage();
    }
  }

  @GetMapping("/alltask")
  public Object alltask(Authentication authentication){
     if (authentication == null || !authentication.isAuthenticated()) {
      return "User is not authenticated. Please log in to change your password.";
    }

    try {
      List<TaskEvent> response = Taskservices.alltasks(authentication.getName());
      return response;
    } catch (Exception e) {
     e.printStackTrace();
      return "Something went wrong during saving..  " + e.getMessage();
    }

  }


}