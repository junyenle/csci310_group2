package whitebox;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import classes.Security;

public class SecurityTests {

	private static String username;
	private static String password = "password";

	@BeforeClass
	public static void setUp() throws Exception {
		// randomize username
		int max = 1000000;
		int min = 1;
		Random randomRotator = new Random();
		int randNum = randomRotator.nextInt((max - min) + 1) + min;
		username = String.valueOf(randNum);
	}

	@Test
	public void testGoodLogin() {
		Security.registerUser(username, password);
		if (Security.authenticate(username, password)) {
			System.out.println("PASS: testGoodLogin");
			return;
		}
		fail("FAIL: testGoodLogin");
	}

	@Test
	public void testBadLogin() {
		Security.registerUser(username, password);
		if (!Security.authenticate(username, "badPassword")) {
			System.out.println("PASS: testBadLogin");
			return;
		}
		fail("FAIL: testBadLogin");
	}

	@Test
	public void testExistingRegistration() {
		if (!Security.registerUser(username, password)) {
			System.out.println("PASS: testExistingRegistration");
			return;
		}
		fail("FAIL: testExistingRegistration");
	}

}
