package com.examples.ezoo.servlets.feedingschedule;

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
 * Servlet implementation class assignFeedingSchedule
 */
@WebServlet("/assignFeedingSchedule")
public class AssignFeedingSchedule extends HttpServlet {
	
	private static final long serialVersionUID = 1;
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FeedingScheduleDAO daoFS = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> feedingSchedules = daoFS.getFeedingSchedules();
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		AnimalDAO daoAn = DAOUtilities.getAnimalDao();
		List<Animal> animals = daoAn.getAllAnimals();
		request.getSession().setAttribute("animals", animals);

		request.getRequestDispatcher("sections/feedingSchedules/assignFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		Long animalId = Long.parseLong(request.getParameter("animal"));
		int feedingScheduleId = Integer.parseInt(request.getParameter("feedingSchedule"));
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();

		try {
			dao.assignFeedingSchedule(animalId, feedingScheduleId);
			request.getSession().setAttribute("message", "Feeding Schedule successfully assigned");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");

		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem assigning the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("sections/feedingSchedules/viewFeedingSchedule.jsp").forward(request, response);

		}
	}

}
