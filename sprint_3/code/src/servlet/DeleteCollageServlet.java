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
 * Delete Collage Servlet Implementation
 */
@WebServlet("/deleteCollageServlet")
public class DeleteCollageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteCollageServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ensuring that HttpSession exists
		HttpSession session = request.getSession(false);
		// If HttpSession does not exist, halt
		if (session == null) {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("failure");
			return;
		}
		// Ensuring that a CollageManager exists
		if (session.getAttribute("collageManager") == null) {
			// If CollageManager does not exist, halt
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("failure");
			return;
		}
		
		// Access CollageManager from HttpSession
		CollageManager manager = (CollageManager) session.getAttribute("collageManager");

		// get request parameters
		String rawIndex = request.getParameter("clickedIndex");
		System.out.println("deleting index: " + rawIndex);
		int index = Integer.valueOf(rawIndex);

		// perform deletion
		boolean collageDeleted = manager.deleteCollage((String) session.getAttribute("username"), index);
		
		// Result string to hold response text
		String result = "";
		if (collageDeleted) {
			result = "success";
		} else {
			result = "failed";
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
		session.setAttribute("collageManager", manager); // Set session attribute to created CollageManager
	} // End service

}