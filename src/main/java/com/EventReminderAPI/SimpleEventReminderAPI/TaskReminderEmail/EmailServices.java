package com.EventReminderAPI.SimpleEventReminderAPI.TaskReminderEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException; // Added missing import
import jakarta.mail.internet.MimeMessage;

import com.EventReminderAPI.SimpleEventReminderAPI.Data.TaskEvent;

@Service
public class EmailServices {

    @Autowired
    public JavaMailSender sender; // Changed to match variable usage below

    @Autowired
    private TemplateEngine templateEngine;

    // Added 'throws MessagingException' to handle checked exceptions from MimeMessageHelper
    public void setEmail(TaskEvent taskEvent) throws MessagingException {
        // FIXED: Changed 'mailSender' to 'sender' to match your Autowired variable name
        MimeMessage message = sender.createMimeMessage(); 
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        helper.setTo(taskEvent.getTaskOwner());
        helper.setSubject("Task Reminder Notifications! - " + taskEvent.getTaskOwner());

        Context context = new Context();
        context.setVariable("title", taskEvent.getTitle());
        context.setVariable("category", taskEvent.getCategory());
        // Note: Kept your spelling of "describution" to match your TaskEvent model
        context.setVariable("describution", taskEvent.getDescribution()); 
        context.setVariable("taskOwner", taskEvent.getTaskOwner());

        String htmlContent = templateEngine.process("eventMessage", context);

        helper.setText(htmlContent, true);

        sender.send(message);
    }
}

