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
 * ServletUpdate class is used to update the vehicle records in the database
 * @author Qasim Awan
 * @version 1.0
 */
public class ServletUpdate extends HttpServlet {
	
	private static final long serialVersionUID= 1L;
	
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
			
			// declare a vehicleDAO object
			VehicleDAO dao = new VehicleDAO();
			
			// declare a vehicle variable
			Vehicle vehicleInfo;
			
			// get the vehicle id
			int vehicle_id = Integer.parseInt(req.getParameter("vehicle_id"));
			try 
			{
				// get the vehicle from the database
				vehicleInfo = dao.getVehicle(vehicle_id);
				
				// describe the path to look withing the ServletContext and store it into view
				RequestDispatcher view = req.getRequestDispatcher("update.jsp");
				
				// set the attributes for the page
				req.setAttribute("vehicleInfo", vehicleInfo);
				view.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			resp.sendRedirect("home");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		// store the data into the variables received using the post method
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
		
		// create a new object vehicle
		Vehicle vehicle = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission,
				mileage, fuel_type, engine_size, body_style, condition, notes, sold);
		
		try 
		{
			// declare a new vehicleDAO object
			VehicleDAO dao = new VehicleDAO();
			
			// udpate the vehicle in the database
			dao.updateVehicle(vehicle, vehicle_id);
			resp.sendRedirect("home");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
