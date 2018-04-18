package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.CollageManager;

/**
 * Save Collage Servlet Implementation
 */
@WebServlet("/saveCollageServlet")
public class SaveCollageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveCollageServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ensuring that HttpSession exists
		HttpSession session = request.getSession(false);
		// If HttpSession does not exist, create one
		if (session == null) {
			session = request.getSession(true);
		}
		// Ensuring that a CollageManager exists
		if (session.getAttribute("collageManager") == null) {
			// If CollageManager does not exist, create one
			CollageManager collageManager = new CollageManager();
			session.setAttribute("collageManager", collageManager); // Set session attribute to created CollageManager
		}
		// Access CollageManager from HttpSession
		CollageManager collageManager = (CollageManager) session.getAttribute("collageManager");

		// save collage
		boolean collageSaved = collageManager.saveCollage();

		// Result string to hold response text
		String result = "";
		if (collageSaved) {
			result = "success";
		} else {
			result = "failed";
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
		session.setAttribute("collageManager", collageManager); // Set session attribute to created CollageManager
	} // End service

}