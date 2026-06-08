package com.EventReminderAPI.SimpleEventReminderAPI.Repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.EventReminderAPI.SimpleEventReminderAPI.Data.userData;



/**
 * Repository interface for managing user data operations.
 * Provides CRUD operations and custom query methods for userData entities
 * stored in MongoDB.
 */
@Repository
public interface userDataRepository  extends MongoRepository<userData, String> {
    
    // Custom query method to find a user by their email address
    userData findByUseremail(String useremail);

}
