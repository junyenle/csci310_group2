package servlet;

import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.imageio.ImageIO;

import java.util.Base64;

import classes.*;

/**
 * Collage Builder Servlet Implementation
 */
@WebServlet("/collageBuilderServlet")
public class CollageBuilderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CollageBuilderServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("received request to build");
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

		// Parsing user input
		String searchText = request.getParameter("searchText");
		String shapeText = request.getParameter("shapeText").toUpperCase();
		int collageBorderWidth = Integer.valueOf(request.getParameter("collageBorderWidth"));
		String collageBorderColor = request.getParameter("collageBorderColor");
		int photoBorderWidth = Integer.valueOf(request.getParameter("photoBorderWidth"));
		String photoBorderColor = request.getParameter("photoBorderColor");
		int minRotation = Integer.valueOf(request.getParameter("minRotation"));
		int maxRotation = Integer.valueOf(request.getParameter("maxRotation"));
		int collageWidth = Integer.valueOf(request.getParameter("collageWidth"));
		int collageHeight = Integer.valueOf(request.getParameter("collageHeight"));
		String filter = request.getParameter("filter");

		// create options object
		CollageOptions options = new CollageOptions(collageBorderWidth, collageBorderColor, photoBorderWidth,
				photoBorderColor, minRotation, maxRotation, collageWidth, collageHeight, filter);

		boolean collageBuildingFailed = false; // Flag to indicate whether the collage building failed

		// Prepare imagesourcer
		int requiredImages = 30;
		int buffer = 10;
		ImageSourcer s = new ImageSourcer(searchText, requiredImages + buffer + searchText.length());
		if (s.isValid()) // if image sourcing worked, build collage
		{
			System.out.println("sufficient images found: on to building!");
			// Prepare collagebuilder
			int browserWidth = Integer.valueOf(request.getParameter("browserWidth").trim());
			int browserHeight = Integer.valueOf(request.getParameter("browserHeight").trim());
			CollageBuilder collageBuilder = new CollageBuilder(options); // Instantiate CollageBuilder object

			BufferedImage collage = collageBuilder.buildCollage(s, shapeText); // CollageBuilder.buildCollage(imageSource)
																				// builds a collage out of the 30
																				// Images returned from the ImageSourcer
																				// and returns it as a BufferedImage.
			collageManager.insertCollage(searchText, collage); // Insert searchText (which will serve as title of
																// collage) and created collage into the CollageManager.
		} else // don't even build collage
		{
			collageBuildingFailed = true;
			System.out.println("insufficient images found");
		}

		// Result string to hold response text
		String result = "";
		if (collageBuildingFailed) {
			result = "fail " + searchText;
			collageManager.wipeCollages();
		} else {
			result = "success";
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
		session.setAttribute("collageManager", collageManager); // Set session attribute to created CollageManager
		session.setAttribute("currentSearchText", searchText);
	} // End service

}