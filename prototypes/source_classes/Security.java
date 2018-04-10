package classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Security {

	public static void main(String[] args)
	{
		System.out.println("this should work only on the first run");
		registerUser("jun", "july");
		System.out.println("");
		
		System.out.println("this should not work");
		registerUser("jun", "jacob");
		System.out.println("");
		
		System.out.println("this should work");
		authenticate("jun", "july");
		System.out.println("");
		
		System.out.println("this should not work");
		authenticate("jun", "jacob");
		System.out.println("");
		
		System.out.println("this should not work");
		authenticate("jeremy", "july");
	}
	
	public static boolean authenticate(String username, String password)
	{
		// hash password
		String hashedPassword = hashPassword(password);
		
		// connect to database
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/scollage?user=root&password=root&useSSL=false");
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM Users WHERE username=? AND password=?");
			ps.setString(1, username); // set first variable in prepared statement
			ps.setString(2,  hashedPassword); // set second variable
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				System.out.println("Successfully logged in user " + username);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Unable to log in user " + username);
		return false;
	}
	
	private static boolean registerUser(String username, String password)
	{
		// hash password
		String hashedPassword = hashPassword(password);
		
		// connect to database
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/scollage?user=root&password=root&useSSL=false");
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1, username); // set first variable in prepared statement
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) // look for a user with that name
			{
				System.out.println("User " + username + " already exists!");
				return false;
			}
			
			// if no user found with that name, allow registration
			ps = (PreparedStatement) conn.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
			ps.setString(1, username);
			ps.setString(2, hashedPassword);
			ps.execute();
			System.out.println("User " + username + " registered.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	// hashes raw password
	private static String hashPassword(String password) {
		String passwordToHash = password;
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			//Add password bytes to digest
			md.update(passwordToHash.getBytes());
			//Get the hash's bytes
			byte[] bytes = md.digest();
			//This bytes[] has bytes in decimal format;
			//Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
				//Get complete hashed password in hex format
				generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
		}
		
		return generatedPassword;
	}
}
