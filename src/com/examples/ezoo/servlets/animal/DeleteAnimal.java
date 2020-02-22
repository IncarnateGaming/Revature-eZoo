package com.examples.ezoo.servlets.animal;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;


/**
 * Servlet implementation class DeleteAnimal
 */
@WebServlet(description = "This servlet allows deleting animals", urlPatterns = { "/deleteAnimal" })
public class DeleteAnimal extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get Feeding Schedule ID
		AnimalDAO dao = DAOUtilities.getAnimalDao();
		Map<String, String[]> params = request.getParameterMap();
		String[] defaultAnimal = {"" + dao.getHighestId()};
		Integer targetAnimalId = Integer.parseInt(params.getOrDefault("feedingScheduleId", defaultAnimal)[0]);

		try {
			dao.deleteAnimal(targetAnimalId);
			request.getSession().setAttribute("message", "Animal successfully deleted");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect("feedingSchedules");
	}
}
