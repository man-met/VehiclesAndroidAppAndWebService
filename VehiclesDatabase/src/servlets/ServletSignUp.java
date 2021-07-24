package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.VehicleDAO;

public class ServletSignUp extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PrintWriter writer;
	// deals with get requests
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// describe the path to look within the ServletContext and store it into view
		RequestDispatcher view = req.getRequestDispatcher("signup.jsp");
		
		// direct to the page take requests and responses along
		view.forward(req, resp);
	}
	
	// deals with the post requests
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		// store the data inputted by the user
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		
		if(!username.equals("") && !password.equals(""))
		{
			// create a user object
			User newUser = new User(-999, firstName, lastName, username, password, 0, null);
			
			// check if the passwords entered match
			if(password.equals(confirmPassword))
			{
				try 
				{
					// declare a vehicle object
					VehicleDAO dao = new VehicleDAO();
					
					// insert the user and retrieve the boolean value
					boolean done = dao.insertUser(newUser);
					
					// checks the boolean value of done
					if(done)
					{
						// redirects to the home page
						resp.sendRedirect("login");
					}
					else
					{
						// prints a message on the browser
						resp.sendRedirect("signUp");
						resp.getWriter().write("Invalid Vehicle License number!");
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
				// redirect to signUp page
				resp.sendRedirect("signUp");
			}
		}
		else
		{
			// print an error on the page as user did not enter details correctly
			writer = resp.getWriter();
			writer.write("Error: Please fill in all the fields");
			writer.close();
		}
		
	}
}
