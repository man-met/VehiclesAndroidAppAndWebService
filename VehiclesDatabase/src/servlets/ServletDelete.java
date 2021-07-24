package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.VehicleDAO;
/**
 * ServletDelete class is used to delete the vehicle record from the database
 * @author Qasim Awan
 * @version 1.0
 */
public class ServletDelete extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	// deals with get methods
	protected void doGet(HttpServletRequest req,HttpServletResponse resp ) throws ServletException, IOException
	{
		// retrieves the session if exists
		HttpSession session = req.getSession(false);
		
		// checks if the session exists and LoggedIn attribute exists
		if(session != null && session.getAttribute("loggedin")!=null)
		{
			// stores the vehicle_id received from the JSP page
			int vehicle_id = Integer.parseInt(req.getParameter("vehicle_id"));
			
			// creates a new vehicleDAO object
			VehicleDAO dao = new VehicleDAO();
			try 
			{
				// deletes the vehicle record from the database
				dao.deleteVehicle(vehicle_id);
				
				// redirects to the home page
				resp.sendRedirect("home");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			// redirects to the home page
			resp.sendRedirect("home");
		}
	}
}
