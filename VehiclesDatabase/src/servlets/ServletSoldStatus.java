package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Sales;
import models.Vehicle;
import models.VehicleDAO;
/**
 *  ServletSoldStatus class is used to change the status of the vehicles sold and enter the sales records
 * @author Qasim Awan
 * @version 1.0
 */
public class ServletSoldStatus extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// deals with get requests
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
			
			// check if available parameter exists
			if(req.getParameter("available") != null)
			{
				// get the value stored in available and store into the variable
				int vehicle_id = Integer.parseInt(req.getParameter("available"));
				
				// set the vehicle id retrieved to use in the JSP file
				req.setAttribute("vehicle_id", vehicle_id);
				
				// describe the path to look withing the ServletContext and store it into view
				RequestDispatcher view = req.getRequestDispatcher("soldStatus.jsp");
				
				// direct to the page and pass requests and responses
				view.forward(req, resp);
			}
			else
			{
				try 
				{
					// get the value stored in the parameter sold and store it into a variable
					int vehicle_id = Integer.parseInt(req.getParameter("sold"));
					
					// declare a VehicleDAO object
					VehicleDAO dao = new VehicleDAO();
					
					// declare a vehicle variable
					Vehicle vehicleInfo;
					
					// declare a boolean
					boolean ok;
					
					// get the vehicle data
					vehicleInfo = dao.getVehicle(vehicle_id);
					
					// set the sold value to false
					vehicleInfo.setSold(false);
					
					// update the vehicle
					ok = dao.updateVehicle(vehicleInfo, vehicle_id);
					
					// check if the update has been successful
					if(ok)
					{
						// delete the vehicle sale record that matches the vehicle_id variable
						ok = dao.deleteVehicleSale(vehicle_id);
						
						// redirect to the home page
						resp.sendRedirect("home");
					}
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			// redirect to the home page
			resp.sendRedirect("home");
		}
	}
	
	// deals with the post request
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try 
		{
			// check if the save parameter exists
			if(req.getParameter("save") != null)
			{
				// get the data from the JSP page and store it into the variables
				int vehicle_id = Integer.parseInt(req.getParameter("vehicle_id"));
				Date sold_date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
				int sold_price = Integer.parseInt(req.getParameter("price"));
				String status = req.getParameter("status");
				VehicleDAO dao = new VehicleDAO();
				Vehicle vehicleInfo;
				boolean ok;
				
				// get the vehicle object using vehicle_id
				vehicleInfo = dao.getVehicle(vehicle_id);
				
				// set the vehicle sold boolean value to true
				vehicleInfo.setSold(true);
				
				// update the vehicle record in the database
				ok = dao.updateVehicle(vehicleInfo, vehicle_id);
				
				// check if the update is successful
				if(ok)
				{
					// create a new sales object
					Sales vehicle_sale = new Sales(-999, sold_date, sold_price, status, vehicle_id);
					
					// insert the sale into the database
					ok = dao.insertSale(vehicle_sale);
					
					// redirect it to the homepage
					resp.sendRedirect("home");
				}
			}
			else
			{
				// redirect it to the home page
				resp.sendRedirect("home");
			}
		} 
		catch (ParseException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
