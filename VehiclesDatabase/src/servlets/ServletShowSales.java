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
import models.Vehicle;
import models.VehicleDAO;
/**
 * ServletShowSales class displays all the records of sold vehicles in a table
 * @author Qasim Awan
 * @version 1.0
 */
public class ServletShowSales extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// deals with the get requests
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// redirects to the home page
		resp.sendRedirect("home");
	}
	
	// deals with the post requests
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
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
				// create an object vehicleDAO
				VehicleDAO dao = new VehicleDAO();
				// declare ArrayList Sales variable
				ArrayList <Sales> allSales;
				
				// get all the sales records from the database
				allSales = dao.getAllSales();
				
				// describe the path to look withing the ServletContext and store it into view
				RequestDispatcher view = req.getRequestDispatcher("allSales.jsp");
				
				// sets the attribute to use in the jstl
				req.setAttribute("allSales", allSales);
				
				// direct to the page take requests and responses along
				view.forward(req, resp);
				
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (ParseException e) 
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
