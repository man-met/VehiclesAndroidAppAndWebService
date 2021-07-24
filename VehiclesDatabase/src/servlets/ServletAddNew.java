package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Vehicle;
import models.VehicleDAO;
/**
 * ServletaddNew class is used to insert a new record into the vehicles database
 * @author Qasim Awan
 * @version 1.0
 */
public class ServletAddNew extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	// deals with the get requests
	protected void doGet(HttpServletRequest req,HttpServletResponse resp ) throws ServletException, IOException
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
		
		// describe the path to look withing the ServletContext and store it into view
		RequestDispatcher view = req.getRequestDispatcher("addNew.jsp");
		
		// direct to the page take requests and responses along
		view.forward(req, resp);

	}
	
	// deals with the post requests
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		// gets the parameters from the JSP file and stores them into the variable
		int vehicle_id = Integer.parseInt(req.getParameter("vehicle_id"));
		String make = req.getParameter("make");
		String model = req.getParameter("model");
		int year = Integer.parseInt(req.getParameter("year"));
		int price = Integer.parseInt(req.getParameter("price"));
		String license_number = req.getParameter("license_number");
		String colour = req.getParameter("colour");
		int number_doors = Integer.parseInt(req.getParameter("number_doors"));
		String transmission = req.getParameter("transmission");
		int mileage = Integer.parseInt(req.getParameter("mileage"));
		String fuel_type = req.getParameter("fuel_type");
		int engine_size = Integer.parseInt(req.getParameter("engine_size"));
		String body_style = req.getParameter("body_style");
		String condition = req.getParameter("condition");
		String notes = req.getParameter("notes");
		boolean sold = false;
		
		// creates a new vehicle object
		Vehicle vehicle = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission,
				mileage, fuel_type, engine_size, body_style, condition, notes, sold);
		
		try 
		{
			// creates a new vehicleDAO object
			VehicleDAO dao = new VehicleDAO();
			
			// inserts the vehicle into the database
			boolean done = dao.insertVehicle(vehicle);
			
			// checks the boolean value of done
			if(done)
			{
				// redirects to the home page
				resp.sendRedirect("home");
			}
			else
			{
				// prints a message on the browser
				resp.getWriter().write("Invalid Vehicle License number!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
