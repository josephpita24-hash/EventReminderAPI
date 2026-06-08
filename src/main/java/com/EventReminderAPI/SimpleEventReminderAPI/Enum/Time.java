package com.EventReminderAPI.SimpleEventReminderAPI.Enum;

import java.time.LocalDateTime;

public enum Time {
    
        TENSECOND(10),
        THIRTSECOND(30),

        ONEMINUTES(60),
        FIVEMINUTES(300);

        private final int seconds;

        Time(int seconds) {
                this.seconds = seconds;
        }

        public LocalDateTime getTime() {
                return LocalDateTime.now().plusSeconds(seconds);
        }
}