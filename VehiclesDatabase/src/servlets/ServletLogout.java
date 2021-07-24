package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * ServletLogout allows the user to logout of the webapp and destroys session
 * @author Qasim Awan
 * @version 1.0
 */
public class ServletLogout extends HttpServlet{
	
	private static final long serialVersionUID =  1L;
	
	// deals with the get requests
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		// checks if the seesion exists
		HttpSession session = req.getSession(false);
		
		// checks if the attributes and the session exists
		if(session != null && session.getAttribute("loggedin")!=null)
		{
			// invalidates the session
			session.invalidate();
		}
		// redirects to the home page
		resp.sendRedirect("home");
	}
}
