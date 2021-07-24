/**
 * Servlet controller to run the server and deal with mappings
 * @author Qasim Awan
 * @version 1.0
 */

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.Configuration.ClassList;

public class ServletController{

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Server server = new Server(8005);
		WebAppContext ctx = new WebAppContext();
		ctx.setResourceBase("webapp");
		ctx.setContextPath("/vehiclesdb");
		
		//config
		ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");
		ClassList classlist = ClassList.setServerDefault(server);
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");
		
		//Mappings
		ctx.addServlet("servlets.ServletHome", "/home");
		ctx.addServlet("servlets.ServletLogin","/login");
		ctx.addServlet("servlets.ServletUpdate", "/update");
		ctx.addServlet("servlets.ServletAddNew", "/addNew");
		ctx.addServlet("servlets.ServletLogout", "/logout");
		ctx.addServlet("servlets.ServletDelete", "/delete");
		ctx.addServlet("servlets.ServletSoldStatus", "/soldStatus");
		ctx.addServlet("servlets.ServletShowSales", "/allSales");
		ctx.addServlet("servlets.ServletAPI", "/simpleServer");
		ctx.addServlet("servlets.ServletSignUp", "/signUp");
		ctx.addServlet("servlets.ServletDisplayApiKey", "/apiKey");
		
		//Setting the handler and setting the server
		server.setHandler(ctx);
		server.start();
		server.join();
	}

}
