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


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String result = "";
		
		boolean validUser = Security.authenticate(username, password);
		
		// If authenticated, create session
		HttpSession session = request.getSession(false); // should be nonexistent until logged in!
		if(validUser)
		{
			result = "success";
			if(session == null) // if right login, ensure session
			{
				session = request.getSession(true);
				session.setAttribute("auth", "auth");
			}
		}
		else
		{
			result = "failure";
			if(session != null) // if wrong login, ensure no session
			{
				session.invalidate();
			}
		}
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	} // End service
	
	
}