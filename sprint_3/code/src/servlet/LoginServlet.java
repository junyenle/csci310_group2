package servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.CollageManager;
import classes.Security;

import sun.misc.BASE64Encoder;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// parse user input
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String result = "";

		// check if user is valid
		boolean validUser = Security.authenticate(username, password);

		// If authenticated, create session
		HttpSession session = request.getSession(false); // should be nonexistent until logged in!
		if (validUser) {
			result = "success";
			if (session == null) // if right login, ensure session
			{
				session = request.getSession(true);
			}
			session.setAttribute("username", username);
			System.out.println("logged in: " + (String) session.getAttribute("username"));

			// read user's data to collage manager
			CollageManager manager = new CollageManager();
			// read monstrous string from file :c
			String filename = "/home/student/Desktop/eclipse/userdata/" + username;
			String filecontent = "";
			try {
				filecontent = new String(Files.readAllBytes(Paths.get(filename)));
			} catch (IOException e) {
				// failed to manage file
			}

			String[] data = filecontent.split("\\|");
			int length = data.length-1;
			System.out.println("discoverd " + length / 2 + " past saved collages");
			for (int i = 0; i < length; i += 2) {
				String title = data[i];
				String collageCode = data[i + 1];

				// decode collageCode
				@SuppressWarnings("restriction")
				sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
				byte[] decodedBytes = decoder.decodeBuffer(collageCode);
				BufferedImage collage = ImageIO.read(new ByteArrayInputStream(decodedBytes));

				// add image to manager
				manager.insertSavedCollage(title, collage);

			}

			// set attribute
			session.setAttribute("collageManager", manager);

		} // end valid user
		else {
			result = "failure";
			if (session != null) // if wrong login, ensure no session
			{
				System.out.println("failed to log in user: " + username);
				session.invalidate();
			}
		} // end invalid user

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	} // End service

}