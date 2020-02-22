package com.examples.ezoo.servlets.feedingschedule;

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
 * Servlet implementation class DeleteFeedingSchedule
 */
@WebServlet(description = "This servlet allows deleting feeding schedules", urlPatterns = { "/deleteFeedingSchedule" })
public class DeleteFeedingSchedule extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get Feeding Schedule ID
		Map<String, String[]> params = request.getParameterMap();
		String[] defaultFeedingSchedule = {"" + FeedingSchedule.getNotFedId()};
		Integer targetFeedingScheduleId = Integer.parseInt(params.getOrDefault("feedingScheduleId", defaultFeedingSchedule)[0]);
		
		//return error if aimed at the not fed schedule, as it is a mandatory record
		if(targetFeedingScheduleId == FeedingSchedule.getNotFedId()) {
			request.getSession().setAttribute("message", "Cannot delete the animal not fed schedule, as this is a mandatory record.");
			request.getSession().setAttribute("messageClass", "alert-danger");
			response.sendRedirect("feedingSchedules");
		}else {
			FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
			try {
				dao.deleteFeedingSchedule(targetFeedingScheduleId);
				request.getSession().setAttribute("message", "Feeding schedule successfully deleted");
				request.getSession().setAttribute("messageClass", "alert-success");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			response.sendRedirect("feedingSchedules");
		}
	}
}
