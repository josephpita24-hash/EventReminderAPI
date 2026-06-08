package com.EventReminderAPI.SimpleEventReminderAPI.Services;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.EventReminderAPI.SimpleEventReminderAPI.Data.TaskEvent;
import com.EventReminderAPI.SimpleEventReminderAPI.Enum.Status;
import com.EventReminderAPI.SimpleEventReminderAPI.Enum.Time;
import com.EventReminderAPI.SimpleEventReminderAPI.Repository.TaskEventRepository;
import com.EventReminderAPI.SimpleEventReminderAPI.Repository.userDataRepository;


@Service
public class taskEventServices {

    private final userDataRepository userDataRepository;
    private final TaskEventRepository taskEventRepository;

    // save task
    public taskEventServices(com.EventReminderAPI.SimpleEventReminderAPI.Repository.userDataRepository userDataRepository,
            TaskEventRepository taskEventRepository) {
        this.userDataRepository = userDataRepository;
        this.taskEventRepository = taskEventRepository;
    }

    public String saveTaskRequest(String tasktOwner, String title, String describution, String category,
            Time eventTime) {

        // 1. Check if there is null value
        if (tasktOwner == null || title == null || describution == null || category == null) {
            return "User input are requere to save task";
        }

        // 2. Check if user Exist in database
        if (userDataRepository.findByUseremail(tasktOwner) == null) {
            return "User: " + tasktOwner + "Could not be found";
        }

        // 3. save task
        TaskEvent task = new TaskEvent();
        task.setTaskOwner(tasktOwner);
        task.setTitle(title);
        task.setCategory(category);
        task.setDescribution(describution);
        task.setEventTime(eventTime);

        taskEventRepository.save(task);

        return "Task: " + title + " Succesessfully";

    }

    public String updateTask(String taskOwner, String oldtitle , String newtitle ,String newdescribution,String newcategory,Status newstatus, Time newTime){
        
        // 1. Check for null value
        if (
            taskOwner == null || oldtitle == null || newtitle == null || newdescribution == null || newcategory == null || newstatus == null || newTime == null
        ) {
            return "User new Input are requered to update task";
        }

        // 2. Check if user Exist in database
        if (userDataRepository.findByUseremail(taskOwner) == null) {
            return "User: " + taskOwner + "Could not be found";
        }

        // 3. Update task
        TaskEvent taskEvent = taskEventRepository.existsBytaskTitleAndtaskOwner(taskOwner,oldtitle).orElseThrow( () -> new UsernameNotFoundException("Task with this title does not exists: " + oldtitle));

        taskEvent.setTitle(newtitle);
        taskEvent.setDescribution(newdescribution);
        taskEvent.setCategory(newcategory);
        taskEvent.setStatus(newstatus);
        taskEvent.setEventTime(newTime);

        taskEventRepository.save(taskEvent);

        return "Task: " + taskEvent + " Saved successfully";
    }

    // delete task
    public String deleteTask(String taskOwner, String oldtitle){

         TaskEvent taskEvent = taskEventRepository.existsBytaskTitleAndtaskOwner(taskOwner,oldtitle).orElseThrow( () -> new UsernameNotFoundException("Task with this title does not exists: " + oldtitle)); 
         if (taskEvent != null) {
            taskEventRepository.delete(taskEvent);
         }

        return "Task: " + taskOwner + " Delete successfully";
        
    }

    // Get all tasks
    public List<TaskEvent> alltasks(String taskOwner){

        return taskEventRepository.findByTaskOwner(taskOwner);
        
    }

    


}
