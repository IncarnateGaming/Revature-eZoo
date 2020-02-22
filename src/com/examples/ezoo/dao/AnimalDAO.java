package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Main interface used to execute CRUD methods on Animal class
 * @author anon
 *
 */
public interface AnimalDAO {

	/**
	 * Used to retrieve a list of all Animals 
	 * @return
	 */
	List<Animal> getAllAnimals();
	List<Animal> getAllAnimals(FeedingSchedule fs);

	/**
	 * Used to persist the animal to the datastore
	 * @param animalToSave
	 */
	void saveAnimal(Animal animalToSave) throws Exception;
	Animal getAnimalById(long id);
	/**
	 * Returns integer of highest id
	 * @return
	 */
	Integer getHighestId();

	/**
	 * Updates an existing animal
	 * @param animalToUpdate
	 */
	void updateAnimal(Animal animalToUpdate) throws Exception;

	void deleteAnimal(Integer animalId) throws Exception;

	
}
