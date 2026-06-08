package com.EventReminderAPI.SimpleEventReminderAPI.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.EventReminderAPI.SimpleEventReminderAPI.Data.TaskEvent;

@Repository
public interface TaskEventRepository extends MongoRepository<TaskEvent,String>{


    List<TaskEvent> findByEventTimeBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Find all user tasks for the given task owner (typically the owner's email).
     *
     * @param taskOwner the identifier of the task owner
     * @return list of userTaskWeather documents owned by the specified user
     */
    List<TaskEvent> findByTaskOwner(String taskOwner);

    /**
     * Check if a task with the given title exists for the specified owner.
     * Uses a custom MongoDB query to match title and taskOwner fields.
     *
     * @param title the title of the task
     * @param taskOwner the owner of the task
     * @return an Optional containing the matching userTaskWeather if found
     */
    @Query("{'title': ?0, 'taskOwner': ?1}")
    Optional<TaskEvent>  existsBytaskTitleAndtaskOwner(String title, String taskOwner);

    /**
     * Count the total number of tasks for a specific user.
     *
     * @param taskOwner the identifier of the task owner
     * @return the total number of tasks owned by the specified user
     */
    @Query(value = "{ 'taskOwner' : ?0 }", count = true)
    Long findTotalTaskOfUser(String taskOwner);



    
}
