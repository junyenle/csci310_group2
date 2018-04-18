package whitebox;

import static org.easymock.EasyMock.createNiceMock;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.Security;
import servlet.LoginServlet;

public class LoginServletTests {
	private LoginServlet servlet;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	private PrintWriter writer;

	@Before
	public void setUp() throws Exception {
		servlet = new LoginServlet();
		request = createNiceMock(HttpServletRequest.class);
		response = createNiceMock(HttpServletResponse.class);
		session = createNiceMock(HttpSession.class);
		writer = createNiceMock(PrintWriter.class);

		Security.registerUser("test user", "test password");
	}

	@After
	public void tearDown() throws Exception {
		session.invalidate();
	}

	@Test
	public void testGoodLogin() {
		EasyMock.expect(request.getParameter("username")).andReturn("test user");
		EasyMock.expect(request.getParameter("password")).andReturn("test password");
		EasyMock.expect(request.getSession(false)).andReturn(null);
		EasyMock.expect(request.getSession(true)).andReturn(session);
		try {
			EasyMock.expect(response.getWriter()).andReturn(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		EasyMock.replay(request);
		EasyMock.replay(session);
		EasyMock.replay(response);

		try {
			servlet.service(request, response);
		} catch (ServletException e) {
			System.out.println("FAIL: testGoodLogin");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testGoodLogin");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testGoodLogin");
	}

	@Test
	public void testBadLogin() {
		EasyMock.expect(request.getParameter("username")).andReturn("test user");
		EasyMock.expect(request.getParameter("password")).andReturn("wrong password");
		EasyMock.expect(request.getSession(false)).andReturn(null);
		EasyMock.expect(request.getSession(true)).andReturn(session);
		try {
			EasyMock.expect(response.getWriter()).andReturn(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		EasyMock.replay(request);
		EasyMock.replay(session);
		EasyMock.replay(response);

		try {
			servlet.service(request, response);
		} catch (ServletException e) {
			System.out.println("FAIL: testBadLogin");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testBadLogin");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testBadLogin");
	}

	@Test
	public void testBadToGoodLogin() {
		EasyMock.expect(request.getParameter("username")).andReturn("test user");
		EasyMock.expect(request.getParameter("password")).andReturn("wrong password");
		EasyMock.expect(request.getSession(false)).andReturn(session);
		EasyMock.expect(request.getSession(true)).andReturn(session);
		try {
			EasyMock.expect(response.getWriter()).andReturn(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		EasyMock.replay(request);
		EasyMock.replay(session);
		EasyMock.replay(response);

		try {
			servlet.service(request, response);
		} catch (ServletException e) {
			System.out.println("FAIL: testBadToGoodLogin");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testBadToGoodLogin");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testBadToGoodLogin");
	}

	@Test
	public void testGoodToGoodLogin() {
		EasyMock.expect(request.getParameter("username")).andReturn("test user");
		EasyMock.expect(request.getParameter("password")).andReturn("test password");
		EasyMock.expect(request.getSession(false)).andReturn(session);
		EasyMock.expect(request.getSession(true)).andReturn(session);
		try {
			EasyMock.expect(response.getWriter()).andReturn(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		EasyMock.replay(request);
		EasyMock.replay(session);
		EasyMock.replay(response);

		try {
			servlet.service(request, response);
		} catch (ServletException e) {
			System.out.println("FAIL: testGoodToGoodLogin");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testGoodToGoodLogin");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testGoodToGoodLogin");
	}
}
