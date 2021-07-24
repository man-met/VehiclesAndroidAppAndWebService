package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;
import models.VehicleDAO;
/**
 * ServletLogin class is allows the user to input username and password and login to the webapp
 * @author Qasim Awan
 * @version 1.0
 */
public class ServletLogin extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	// deals with get requests
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// describe the path to look within the ServletContext and store it into view
		RequestDispatcher view = req.getRequestDispatcher("login.jsp");
		
		// direct to the page take requests and responses along
		view.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		// get the parameter received and store into the variables
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		// hash the password received
		String hashedPassword = toMD5Hash(password);
		
		// declare a vehicleDAO object
		VehicleDAO dao = new VehicleDAO();
		
		// declare a User variable
		User verifyUser;
		try 
		{
			// get the user from the database
			verifyUser = dao.getUser(username);
			
			// check if the user has been retrieved
			if(verifyUser != null)
			{
				// check if the user credentials entered match wi the credentials received from the database
				if(verifyUser.getUsername().equals(username) && verifyUser.getPassword().equals(hashedPassword))
				{
					// start a session
					HttpSession session = req.getSession();
					
					// create an attribute LoggedIn and store boolean value true
					session.setAttribute("loggedin", true);
					
					session.setAttribute("username", verifyUser.getUsername());
					// set the session expiry time to
					session.setMaxInactiveInterval(15*60);
					
					//redirect to home page
					resp.sendRedirect("home");
				}
				else
				{
					// redirect to login page
					resp.sendRedirect("login");
				}
			}
			else
			{
				// redirect to login page
				resp.sendRedirect("login");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * method converts the password received from the user to hash
	 * @param password, password is the parameter received which is entered by the user
	 * @return hashedPassword, returs the password after converting it to a hash
	 */
	protected String toMD5Hash (String password)
	{
		// declare a variable
		String hashedPassword = "";
		try {
			// tell which hash version to use
			MessageDigest md5 = MessageDigest.getInstance( "MD5" );
			
			// convert the password
			md5.update( password.getBytes());
			
			// store it into the md5HasBytes as bytes
			byte [] md5HashBytes = md5.digest( password.getBytes() );
			
			// store it as a BigInteger
			BigInteger number = new BigInteger(1, md5HashBytes);
			
			// convert it to a string and store into a string variable
			hashedPassword = number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return the hash string
		return hashedPassword;
		
	}
	
}
