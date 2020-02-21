/**
 * This listener class runs once on startup and makes sure that required records exist.
 */
package startup;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

@WebListener
public class Listener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/**
		 * Make sure that the not fed Feeding Schedule exists
		 */
		FeedingScheduleDAO daoFS = DAOUtilities.getFeedingScheduleDao();
		FeedingSchedule notFed = daoFS.getFeedingSchedule(FeedingSchedule.getNotFedId());
		if(null == notFed.getFood()) {
			FeedingSchedule fs = new FeedingSchedule(
					FeedingSchedule.getNotFedId(),
					"never",
					"never",
					"nothing",
					"save this poor animal by assigning it a feeding schedule before it starves"
					);
			try {
				daoFS.saveFeedingSchedule(fs);
				System.out.println("Created an Unfed Animals feeding schedule");
			} catch (Exception e) {
				System.out.println("Failed to create feeding schedule -1 for unfed animals");
				e.printStackTrace();
			}
		}else {
			String newLine = System.getProperty("line.separator");
			System.out.println("Current not fed Feeding Schedule is:"+newLine+notFed);
		}
		/**
		 * Make sure that all animals have a feeding schedule assigned.
		 */
		// Check to see if the default integer value for SQL exists as an actual Feeding Schedule, only run if it does not
		if(null == daoFS.getFeedingSchedule(0).getFood()) {
			AnimalDAO daoAn = DAOUtilities.getAnimalDao();
			List<Animal> animals = daoAn.getAllAnimals();
			for(Animal animal : animals) {
				if(0 == animal.getFeedingScheduleId()) {
					animal.setFeedingScheduleId(FeedingSchedule.getNotFedId());
				}
			}
		}

		/**
		 * Call super
		 */
		ServletContextListener.super.contextInitialized(sce);
	}
}
