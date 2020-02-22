package com.examples.ezoo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;

public class FeedingSchedule {
	/*
	 * Variable Declarations
	 */
	static private int notFedId = -1;
	private int schedule_id;
	private String feedingTime;
	private String recurrence;
	private String food;
	private String notes;
/**
TODO if the website gets heavy traffic it will be more efficient to store the animal
name lists and links within the database and only regenerate them when either the animal
or the feeding schedule is edited.
 */
//	private String animalNames = "";
//	private String animalLinks = "";
	/*
	 * Constructors
	 */
	public FeedingSchedule() {
		super();
	}
	//TODO remove schedule id from here and automate it by detecting highest id in database and adding 1
	public FeedingSchedule(int schedule_id, String feedingTime, String recurrence, String food, String notes) {
		super();
		setScheduleID(schedule_id);
		setFeedingTime(feedingTime);
		setRecurrence(recurrence);
		setFood(food);
		setNotes(notes);
	}
	public FeedingSchedule(ResultSet rs){
		super();
		try {
			setScheduleID(rs.getInt("schedule_id"));
			setFeedingTime(rs.getString("feeding_time"));
			setRecurrence(rs.getString("recurrence"));
			setFood(rs.getString("food"));
			setNotes(rs.getString("notes"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Getters and Setters
	 */
	static public int getNotFedId() {
		return FeedingSchedule.notFedId;
	}
	public int getScheduleID() {
		return this.schedule_id;
	}
	public FeedingSchedule setScheduleID(int id) {
		this.schedule_id = id;
		return this;
	}
	public String getFeedingTime() {
		return this.feedingTime;
	}
	public FeedingSchedule setFeedingTime(String feedingTime) {
		this.feedingTime = feedingTime;
		return this;
	}
	public String getRecurrence() {
		return this.recurrence;
	}
	public FeedingSchedule setRecurrence(String recurrence) {
		this.recurrence = recurrence;
		return this;
	}
	public String getFood() {
		return this.food;
	}
	public FeedingSchedule setFood(String food) {
		this.food = food;
		return this;
	}
	public String getNotes() {
		return this.notes;
	}
	public FeedingSchedule setNotes(String notes) {
		this.notes = notes;
		return this;
	}
	public String getAnimalNames() {
		AnimalDAO daoAn = DAOUtilities.getAnimalDao();
		List<Animal> animals = daoAn.getAllAnimals(this);
		Integer animalSize = animals.size();
		StringBuilder sb = new StringBuilder();
		if(1 <= animalSize) {
			sb.append(animals.get(0).getName());
			for(Integer x=1; x<animalSize; x++) {
				sb.append(", "+animals.get(x).getName());
			}
		}else {
			sb.append("No Animals Applied");
		}
		String result = sb.toString();
		return result;
	}
	public String getAnimalLinks() {
		AnimalDAO daoAn = DAOUtilities.getAnimalDao();
		List<Animal> animals = daoAn.getAllAnimals(this);
		Integer animalSize = animals.size();
		StringBuilder sb = new StringBuilder();
		if(1 <= animalSize) {
			sb.append("<a href=\"/eZoo/editAnimal?animalid="+ animals.get(0).getAnimalID() +"\">" + animals.get(0).getName() + "</a>");
			for(Integer x=1; x<animalSize; x++) {
				sb.append(", <a href=\"/eZoo/editAnimal?animalid="+ animals.get(x).getAnimalID() +"\">" + animals.get(x).getName() + "</a>");
			}
		}else {
			sb.append("No Animals Applied");
		}
		String result = sb.toString();
		return result;
	}
	/*
	 * Overrides
	 */
	@Override
	public String toString() {
		return "Feeding Schedule [schedule_id=" + getScheduleID() + ", feeding_time=" + getFeedingTime() + ", recurrennce=" 
				+ getRecurrence() + ", food=" + getFood() + ", notes=" + getNotes() + "]";
	}
}
