package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.Vehicle;
import models.VehicleDAO;

/**
 * a class used to deal with requests
 * @author qasimak
 * @version 1.0
 */
public class ServletAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// declare the variables and object instances
	VehicleDAO dao = new VehicleDAO();
	Gson gson = new Gson();
	PrintWriter writer;
	Vehicle thisVehicle;
	
	/**
	 * method to deal with the get requests
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// declare a message variable
		String message = "";
		
		// check if the api_key is null
		if(req.getParameter("api_key") != null)
		{
			// store the api_key into a variable
			String api_key = req.getParameter("api_key");
			
			try 
			{
				// check if the api_key exists
				boolean match = dao.checkAPIKey(api_key);
				
				// check if the boolean is tru
				if(match)
				{
					try
					{
						// declare an arraylist of vehicles objects
						ArrayList<Vehicle> allVehicles = dao.getAllVehicles();
						
						// convert the arraylist to a json arraylist
						String jsonVehicles = gson.toJson(allVehicles);
						writer = resp.getWriter();
						// respond the request
						writer.write(jsonVehicles);
						// close the writer
						writer.close();
					} 
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					// store the message
					message = "Error: Invalid API Key";
				}
				
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else
		{
			// store the message
			message = "Error: Access to this API requires an API Key";
		}
		
		// respond to the request
		writer = resp.getWriter();
		// send a message
		writer.write(message);
		// close the writer
		writer.close();
	}
	
	/**
	 * method to deal with the post requests
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// declare a message variable to store the messages
		String message = "";
		
		// check if the api_key is null
		if(req.getParameter("api_key") != null)
		{
			// store the api_key into a variable
			String api_key = req.getParameter("api_key");
			
			try 
			{
				// check if the api_key matches with api_key in the database
				boolean match = dao.checkAPIKey(api_key);
				
				// check for the boolean value
				if(match)
				{
					try 
					{
						// store the vehicle object from json to vehicle object
						thisVehicle = gson.fromJson(req.getParameter("json"), Vehicle.class);
						
						// insert the vehicle
						dao.insertVehicle(thisVehicle);
						
						// store the message
						message = "Vehicle Inserted";
					} 
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					//store the message
					message = "Error: Invalid API Key";
				}
				
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else
		{
			// store the message
			message = "Error: Access to this API requires an API Key";
		}
		// respond to the request
		writer = resp.getWriter();
		// send a message
		writer.write(message);
		// close the writer
		writer.close();
		
	}

	/**
	 * method to deal with put requests
	 */
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// declare a message variable to store the message
		String message = "";
		
		// check if the api_key is null
		if(req.getParameter("api_key") != null)
		{
			// store the api_key into the variable
			String api_key = req.getParameter("api_key");
			
			try 
			{
				// check for the api_key in the database
				boolean match = dao.checkAPIKey(api_key);
				
				// check for the boolean value
				if(match)
				{
					try 
					{
						// convert the vehicle from json
						thisVehicle = gson.fromJson(req.getParameter("json"), Vehicle.class);
						// update the vehicle
						dao.updateVehicle(thisVehicle, thisVehicle.getVehicle_id());
						// store the message
						message = "Vehicle Updated";
					} 
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						// store the message
						message = "Error: error updating the vehicle";
						e.printStackTrace();
					}
				}
				else
				{
					// store the message
					message = "Error: Invalid API Key";
				}
				
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else
		{
			// stpre message
			message = "Error: Access to this API requires an API Key";
		}
		// respond to the request
		writer = resp.getWriter();
		// send a message
		writer.write(message);
		// close the writer
		writer.close();
	}
	
	// method to deal with the delete requests
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// declare a message variable to store a message
		String message = "";
		
		// check if the api_key received is null
		if(req.getParameter("api_key") != null)
		{
			// store the api_key into the string variable
			String api_key = req.getParameter("api_key");
			
			try 
			{
				// check if the api_key matches the api_key in the database
				boolean match = dao.checkAPIKey(api_key);
				
				// check the boolean value
				if(match)
				{
					// store the vehicle_id received
					int vehicle_id = Integer.parseInt(req.getParameter("vehicle_id"));

					try 
					{
						// delete vehicle and store the boolean value
						boolean done = dao.deleteVehicle(vehicle_id);
						
						// check the boolean value in done
						if(done)
						{
							// store the message
							message = "Vehicle successfully deleted";
						}
						else
						{
							// store the message
							message = "Error: Error deleting the vehicle";
						}
					} 
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					// store the message
					message = "Error: Invalid API Key";
				}
				
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else
		{
			// store the message
			message = "Error: Access to this API requires an API Key";
		}
		//respond to the request
		writer = resp.getWriter();
		// send the message
		writer.write(message);
		// close the writer
		writer.close();
	}
}
