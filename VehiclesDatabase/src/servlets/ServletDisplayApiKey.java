package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Sales;
import models.User;
import models.VehicleDAO;

public class ServletDisplayApiKey extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// redirect to the home page
		resp.sendRedirect("home");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// retrieves the session if exists
		HttpSession session = req.getSession(false);
				
		// checks if the session exists and LoggedIn attribute exists
		if(session != null && session.getAttribute("loggedin")!=null)
		{
			// creates a boolean variable and stores the true or false by getting the attribute from the session LoggedIn
			boolean loggedin = (boolean) session.getAttribute("loggedin");
			
			// set it to use in jstl
			req.setAttribute("loggedin", loggedin);
			try 
			{
				String username = (String) session.getAttribute("username");
				
				// create an object vehicleDAO
				VehicleDAO dao = new VehicleDAO();
				
				// get the user object from the database
				User user = dao.getUser(username);
				
				// describe the path to look withing the ServletContext and store it into view
				RequestDispatcher view = req.getRequestDispatcher("apiKey.jsp");

				// sets the attribute to use in the jstl
				req.setAttribute("firstName", user.getFirst_name());
				req.setAttribute("lastName", user.getLast_name());
				req.setAttribute("apiKey", user.getApi_key());
							
				// direct to the page take requests and responses along
				view.forward(req, resp);
				
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		else
		{
			// redirect to the home page
			resp.sendRedirect("home");
		}
	}

}
