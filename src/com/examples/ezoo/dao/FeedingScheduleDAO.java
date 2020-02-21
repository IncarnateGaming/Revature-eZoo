package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Main interface to execute CRUD methods on FeedingSchedule class
 * @author ProNobis
 *
 */
public interface FeedingScheduleDAO {
	/**
	 * Save a new feeding schedule to the DB
	 * @param fs - new feeding schedule to be added to DB
	 */
	void saveFeedingSchedule(FeedingSchedule fs) throws Exception;
	/**
	 * Calls an existing feeding schedule and updates its fields.
	 * @param fs
	 * @throws Exception
	 */
	void editFeedingSchedule(FeedingSchedule fs) throws Exception;
	/**
	 * Delete a feeding schedule from the DB and remove references to it from all animals
	 * @param fs
	 */
	void deleteFeedingSchedule(FeedingSchedule fs) throws Exception;
	/**
	 * Return a list of all feeding schedules
	 * @return
	 */
	List<FeedingSchedule> getFeedingSchedules();
	/**
	 * Return the feeding schedule for a specific animal
	 * @param animal
	 * @return
	 */
	FeedingSchedule getFeedingSchedule(Animal animal);
	/**
	 * Return the feeding schedule by id
	 * @param scheduleId
	 * @return
	 */
	FeedingSchedule getFeedingSchedule(Integer scheduleId);
	/**
	 * Assign a feeding schedule to a specific animal
	 * @param animal
	 * @param fs
	 */
	void assignFeedingSchedule(Animal animal, FeedingSchedule fs) throws Exception;
	/**
	 * Remove a feeding schedule from an animal, include a message to remind them to assign a new feeding schedule lest the animal not be fed
	 * @param animal
	 */
	void removeFeedingSchedule(Animal animal) throws Exception;
}
