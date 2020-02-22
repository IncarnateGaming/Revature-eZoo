package com.examples.ezoo.servlets.animal;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

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
 * Servlet implementation class AddAnimalServlet
 */
@WebServlet("/addAnimal")
public class AddAnimalServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Grab a list of Feeding Schedules from the Database
		FeedingScheduleDAO daoFS = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> feedingSchedules = daoFS.getFeedingSchedules();
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		// Grab highest ID then increase by 1
		AnimalDAO daoAn = DAOUtilities.getAnimalDao();
		Integer newId = daoAn.getHighestId() + 1;
		request.getSession().setAttribute("newId", newId);

		request.getRequestDispatcher("sections/animals/addAnimal.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		//We MUST convert to a Long since parameters are always Strings
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
		Animal animalToSave = new Animal(
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
			dao.saveAnimal(animalToSave);
			request.getSession().setAttribute("message", "Animal successfully created");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");


		}catch(SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "Id of " + animalToSave.getAnimalID() + " is already in use");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("sections/animals/addAnimal.jsp").forward(request, response);
			
		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem creating the animal at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("sections/animals/addAnimal.jsp").forward(request, response);

		}
	}

}
