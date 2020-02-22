package com.examples.ezoo.servlets.animal;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class EditAnimalServlet
 */
@WebServlet(description="Enables editing animals", urlPatterns= {"/editAnimal"})
public class EditAnimalServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get animal to be edited
		AnimalDAO daoAn = DAOUtilities.getAnimalDao();
		Map<String, String[]> params = request.getParameterMap();
		String[] defaultAnimalId = {"" + daoAn.getHighestId()};//Animal with highest id will be used if invalid id is passed
		params.getOrDefault("animalid", defaultAnimalId);
		Integer targetAnimalId = Integer.parseInt(params.get("animalid")[0]);

		Animal animal = daoAn.getAnimalById(targetAnimalId);
		request.getSession().setAttribute("animal", animal);

		// Grab a list of Feeding Schedules from the Database
		FeedingScheduleDAO daoFS = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> feedingSchedules = daoFS.getFeedingSchedules();
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);

		request.getRequestDispatcher("sections/animals/editAnimal.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		long id = Long.parseLong(request.getParameter("id"));
		
		String name = request.getParameter("name");

		String kingdom = request.getParameter("kingdom");
		String phylum = request.getParameter("phylum");
		String clazz = request.getParameter("clazz");
		String order = request.getParameter("order");
		String family = request.getParameter("family");
		String genus = request.getParameter("genus");
		String species = request.getParameter("species");
		String type = request.getParameter("type");
		String healthStatus = request.getParameter("healthStatus");
		Integer feedingSchedule = Integer.parseInt(request.getParameter("feedingSchedule"));
		
		//Since request parameters are ALWAYS Strings we will convert them to Double
		double height = Double.parseDouble(request.getParameter("height"));
		double weight = Double.parseDouble(request.getParameter("weight"));
		
		//Create an Animal object from the parameters
		Animal animalToUpdate = new Animal(
				id, 
				name, 
				kingdom,
				phylum,
				clazz,
				order,
				family,
				genus,
				species,
				height,
				weight,
				type,
				healthStatus,
				feedingSchedule);
		
		//Call DAO method
		AnimalDAO dao = DAOUtilities.getAnimalDao();
		try {
			dao.updateAnimal(animalToUpdate);
			request.getSession().setAttribute("message", "Animal successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");


		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem updating the animal at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("sections/animals/editAnimal.jsp").forward(request, response);

		}
	}

}
