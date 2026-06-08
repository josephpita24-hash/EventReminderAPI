package com.EventReminderAPI.SimpleEventReminderAPI.Request;

import com.EventReminderAPI.SimpleEventReminderAPI.Enum.Status;
import com.EventReminderAPI.SimpleEventReminderAPI.Enum.Time;

public class updateTask {
    
    private String oldtitle;
    private String newtitle;
    private String newdescription;
    private String newcategory;
    private Status newstatus;
    private Time newTime;

    
    public Time getNewTime() {
        return newTime;
    }
    public void setNewTime(Time newTime) {
        this.newTime = newTime;
    }
    public String getOldtitle() {
        return oldtitle;
    }
    public void setOldtitle(String oldtitle) {
        this.oldtitle = oldtitle;
    }
    public String getNewtitle() {
        return newtitle;
    }
    public void setNewtitle(String newtitle) {
        this.newtitle = newtitle;
    }
    public String getNewdescription() {
        return newdescription;
    }
    public void setNewdescription(String newdescription) {
        this.newdescription = newdescription;
    }
    public String getNewcategory() {
        return newcategory;
    }
    public void setNewcategory(String newcategory) {
        this.newcategory = newcategory;
    }
    public Status getNewstatus() {
        return newstatus;
    }
    public void setNewstatus(Status newstatus) {
        this.newstatus = newstatus;
    }

   
}
