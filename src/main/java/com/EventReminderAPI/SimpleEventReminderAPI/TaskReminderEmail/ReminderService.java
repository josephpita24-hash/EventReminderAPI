package com.EventReminderAPI.SimpleEventReminderAPI.TaskReminderEmail;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.EventReminderAPI.SimpleEventReminderAPI.Data.TaskEvent;
import com.EventReminderAPI.SimpleEventReminderAPI.Enum.Status;
import com.EventReminderAPI.SimpleEventReminderAPI.Repository.TaskEventRepository;

@Component
public class ReminderService {

    private final TaskEventRepository taskEventRepository;
    private final EmailServices Email;

    public ReminderService(TaskEventRepository taskEventRepository, EmailServices email) {
        this.taskEventRepository = taskEventRepository;
        Email = email;
    }

    @Scheduled(initialDelay = 5000,fixedDelay = 10000)
    public void setReminder() {

        // 1. Get targerted tasks
        List<TaskEvent> TaskToSent = taskEventRepository.findByEventTimeBetween(LocalDateTime.now(),
                LocalDateTime.now().plusSeconds(10));

        if (TaskToSent == null || TaskToSent.isEmpty()) {
         System.out.println("No task Seems to Sent");
            return;
        }

        // 2. Filter tasks
        List<TaskEvent> TasksFilter = TaskToSent.stream().filter(tasks -> tasks.getStatus().equals(Status.PENDING))
                .toList();

        try {
            // 3. Send Emails
            for (TaskEvent task : TasksFilter) {
                // send to email
                Email.setEmail(task);

                // Update status
                task.setStatus(Status.SENT);

                // save
                taskEventRepository.save(task);

                System.err.println("Task: " + task.getTitle() + "Sent Successfully");

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
