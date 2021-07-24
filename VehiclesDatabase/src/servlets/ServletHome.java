package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Vehicle;
import models.VehicleDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ServletHome extends HttpServlet {
	
	private static final long serialVersionUID= 1L;
	
	// deals with get requests
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		try 
		{
			// retrieves the session if exists
			HttpSession session = req.getSession(false);
			
			// checks if the session exists and LoggedIn attribute exists
			if(session != null && session.getAttribute("loggedin")!=null)
			{
				// creates a boolean variable and stores the true or false by getting the attribute from the session LoggedIn
				boolean loggedin = (boolean) session.getAttribute("loggedin");
				
				// sets the attribute LoggedIn to use in the JSP file by getting the boolean variable and giving it a name
				req.setAttribute("loggedin", loggedin);
			}
			
			// creates a vehicleDAO object
			VehicleDAO dao = new VehicleDAO();
			
			// declare a vehicle ArrayList
			ArrayList<Vehicle> allVehicles;
			
			// get all the vehicles from the database and store into the ArrayList
			allVehicles = dao.getAllVehicles();
			
			// describe the path to look withing the ServletContext and store it into view
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			// set the attribute allVehicles by passing the allVehicles ArrayList variable to be able to access in JSP file
			req.setAttribute("allVehicles", allVehicles);
			
			// direct to the page take requests and responses along
			view.forward(req, resp);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// deals with the post requests
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// declares a vehicleDAO object
		VehicleDAO dao = new VehicleDAO();
		
		// declare a vehicle ArrayList
		ArrayList<Vehicle> allVehicles;
		
		// retrieves session if exists
		HttpSession session = req.getSession(false);
		
		// checks if the session exists and LoggedIn attribute exists
		if(session != null && session.getAttribute("loggedin")!=null)
		{
			// creates a boolean variable and stores the true or false by getting the attribute from the session LoggedIn
			boolean loggedin = (boolean) session.getAttribute("loggedin");
			
			// sets the attribute LoggedIn to use in the JSP file by getting the boolean variable and giving it a name
			req.setAttribute("loggedin", loggedin);
			
			// check if sold parameter exists
			if(req.getParameter("sold") != null)
			{
				// redirect to home page
				resp.sendRedirect("home");
			}
			else
			{
				// search the vehicles that match the criteria....
				
				// get the search by parameter and store it into the variable
				String searchBy = req.getParameter("searchBy");
				
				// check if searchby attribute matches one of the strings
				if(searchBy.equals("make") || searchBy.equals("model"))
				{
					// store the value received from the JSP page into the variable
					String value1 = req.getParameter("value1");
					try 
					{
						// search for vehicles that match the criteria running the method from vehicleDAO
						allVehicles = dao.searchVehicles(value1);
						
						// describe the path to look withing the ServletContext and store it into view
						RequestDispatcher view = req.getRequestDispatcher("index.jsp");
						
						// set the attribute allVehicles by passing the allVehicles ArrayList variable to be able to access in JSP file
						req.setAttribute("allVehicles", allVehicles);
						
						// direct to the page take requests and responses along
						view.forward(req, resp);
					}
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// check if search by matches the string price_range
				else if(searchBy.equals("price_range"))
				{
					// check if the parameter to retrieve exist
					if(req.getParameter("value1") != null && req.getParameter("value2") != null)
					{
						// store the values into the variables
						int value1 = Integer.parseInt(req.getParameter("value1"));
						int value2 = Integer.parseInt(req.getParameter("value2"));
						
						try 
						{
							// search vehicles and retrieve from database that are within price range
							allVehicles = dao.searchByPriceRange(value1, value2);
							
							// describe the path to look withing the ServletContext and store it into view
							RequestDispatcher view = req.getRequestDispatcher("index.jsp");
							
							// set the attribute allVehicles by passing the allVehicles ArrayList variable to be able to access in JSP file
							req.setAttribute("allVehicles", allVehicles);
							
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
						// redirect to home page
						resp.sendRedirect("home");
					}
					
				}
				else
				{
					// redirect to home page
					resp.sendRedirect("home");
				}
			}
		}
		
	}
}
