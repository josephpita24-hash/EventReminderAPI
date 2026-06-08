package com.EventReminderAPI.SimpleEventReminderAPI.Request;

import com.EventReminderAPI.SimpleEventReminderAPI.Enum.Status;
import com.EventReminderAPI.SimpleEventReminderAPI.Enum.Time;

public class saveTaskRequest {
    
    private String title;
    private String description;
    private String category;
    private Status status;
    private Time time;

    public saveTaskRequest() {
        this.status = Status.PENDING;
    }
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public String getCategory(){return category;}
    public Status getStatus(){return status;}
    public Time getTime(){return time;}

    public void setTitle(String title){this.title = title;}
    public void setDescription(String description){this.description = description;}
    public void setCategory(String category){this.category = category;}
    public void setStatus(Status status){this.status = status;}
    public void setTime (Time time ){this.time = time;};
}
