package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;


/**
 * Servlet implementation class EditFeedingSchedule
 */
@WebServlet(description = "This servlet allows editing existing feeding schedules", urlPatterns = { "/editFeedingSchedule" })
public class EditFeedingSchedule extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("addAnimal.jsp").forward(request, response);

		//Get Feeding Schedule ID
		Map<String, String[]> params = request.getParameterMap();
		String[] defaultFeedingSchedule = {"" + FeedingSchedule.getNotFedId()};
		params.getOrDefault("feedingScheduleId", defaultFeedingSchedule);
		Integer targetFeedingScheduleId = Integer.parseInt(params.get("feedingScheduleId")[0]);

		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		FeedingSchedule fs = dao.getFeedingSchedule(targetFeedingScheduleId);
		request.getSession().setAttribute("feedingSchedule", fs);

		request.getRequestDispatcher("editFeedingSchedule.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
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
			dao.editFeedingSchedule(fs);
			request.getSession().setAttribute("message", "Feeding Schedule successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");

		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem editing the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("editFeedingSchedule.jsp").forward(request, response);

		}
	}


}
