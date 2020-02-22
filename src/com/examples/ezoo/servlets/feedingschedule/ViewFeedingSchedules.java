package com.examples.ezoo.servlets.feedingschedule;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class ViewFeedingSchedule
 */
@WebServlet("/feedingSchedules")
public class ViewFeedingSchedules extends HttpServlet {
	
	private static final long serialVersionUID = 1;
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Grab a list of Feeding Schedules from the Database
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> feedingSchedules = dao.getFeedingSchedules();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		
		
		request.getRequestDispatcher("sections/feedingSchedules/viewFeedingSchedules.jsp").forward(request, response);
	}
	

}
