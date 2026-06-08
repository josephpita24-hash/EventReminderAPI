package com.EventReminderAPI.SimpleEventReminderAPI.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.EventReminderAPI.SimpleEventReminderAPI.Enum.Status;
import com.EventReminderAPI.SimpleEventReminderAPI.Enum.Time;

@Document("TaskEvent")
public class TaskEvent {

     @Id
     private String taskId;

     private String TaskOwner;
     private String title;
     private String describution;
     private String category;

     private LocalDateTime eventTime;
     private Status status;

     public TaskEvent() {
          this.status = Status.PENDING;
     }

     public String getTaskId() {
          return taskId;
     }

     public void setTaskId(String taskId) {
          this.taskId = taskId;
     }

     public String getTaskOwner() {
          return TaskOwner;
     }

     public void setTaskOwner(String taskOwner) {
          TaskOwner = taskOwner;
     }

     public String getTitle() {
          return title;
     }

     public void setTitle(String title) {
          this.title = title;
     }

     public String getDescribution() {
          return describution;
     }

     public void setDescribution(String describution) {
          this.describution = describution;
     }

     public String getCategory() {
          return category;
     }

     public void setCategory(String category) {
          this.category = category;
     }

     public LocalDateTime getEventTime() {
          return eventTime;
     }

     public void setEventTime(Time time) {
          this.eventTime = time.getTime() ;
     }

     public Status getStatus() {
          return status;
     }

     
     public void setStatus(Status status) {
          this.status = status;
     }

}
