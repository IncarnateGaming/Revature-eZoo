package com.examples.ezoo.test;

import java.util.List;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.AnimalDaoImpl;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.dao.FeedingScheduleDaoImpl;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class TestFeedingScheduleDAO {
	public static void main(String[] args) {
//		runTest();
	}
	public static void runTest() {
		FeedingScheduleDAO dao = new FeedingScheduleDaoImpl();
		AnimalDAO animalDao = new AnimalDaoImpl();
		printOut(dao);
		System.out.println("Create new feeding schedule");
		FeedingSchedule fs = new FeedingSchedule(2,"dinner","daily","banana","leave the peal on");
		try {
			dao.saveFeedingSchedule(fs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		printOut(dao);
		System.out.println("Call feeding schedule of an animal");
		Animal findAnimal = animalDao.getAnimalById(1);
		System.out.println(dao.getFeedingSchedule(findAnimal));
		System.out.println("Assign Different feeding schdule");
		try {
			dao.assignFeedingSchedule(findAnimal, fs);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		findAnimal = animalDao.getAnimalById(1);
		System.out.println(findAnimal);
		System.out.println("Remove feeding schedule");
		try {
			dao.removeFeedingSchedule(findAnimal);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		findAnimal = animalDao.getAnimalById(1);
		System.out.println(findAnimal);
		System.out.println("Delete the feeding schedule that was added");
		printOut(dao);
		try {
			dao.deleteFeedingSchedule(fs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		printOut(dao);
	}
	public static void printOut(FeedingScheduleDAO dao) {
		try {
			System.out.println("Print current feeding schedules:");
			List<FeedingSchedule> feedingSchedules = dao.getFeedingSchedules();
			int feedingSchedulesSize = feedingSchedules.size();
			for (int a =0; a<feedingSchedulesSize; a++) {
				FeedingSchedule fs = feedingSchedules.get(a);
				System.out.println(fs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
