package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class AddFeedingSchedule
 */
@WebServlet("/addFeedingSchedule")
public class AddFeedingSchedule extends HttpServlet {
	
	private static final long serialVersionUID = 1;
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		//We MUST convert to a Int since parameters are always Strings
		int id = Integer.parseInt(request.getParameter("schedule_id"));
		
		String feedingTime = request.getParameter("feeding_time");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		
		//Create an Feeding Schedule object from the parameters
		FeedingSchedule fs = new FeedingSchedule(
				id,
				feedingTime,
				recurrence,
				food,
				notes
				);
		
		//Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.saveFeedingSchedule(fs);
			request.getSession().setAttribute("message", "Feeding Schedule successfully created");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");


		}catch(SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "Id of " + fs.getScheduleID() + " is already in use");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);
			
		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem creating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);

		}
	}

}
