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
import org.junit.Before;
import org.junit.Test;

import classes.CollageManager;
import classes.CollageOptions;
import servlet.CollageBuilderServlet;

public class CollageBuilderServletTests {
	private CollageBuilderServlet servlet;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	private CollageManager collageManager;
	private PrintWriter writer;
	private CollageOptions options;

	@Before
	public void setUp() throws Exception {
		servlet = new CollageBuilderServlet();
		request = createNiceMock(HttpServletRequest.class);
		response = createNiceMock(HttpServletResponse.class);
		session = createNiceMock(HttpSession.class);
		collageManager = new CollageManager();
		writer = createNiceMock(PrintWriter.class);
	}

	@Test
	public void testServletInitialState() {
		// simulates the first call to the servlet, before establishment of session
		EasyMock.expect(request.getSession(false)).andReturn(null);
		EasyMock.expect(request.getSession(true)).andReturn(session).times(2);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(null);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(collageManager);
		EasyMock.expect(request.getParameter("searchText")).andReturn("whales");
		EasyMock.expect(request.getParameter("shapeText")).andReturn("ocean3");
		EasyMock.expect(request.getParameter("collageBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("collageBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("photoBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("photoBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("minRotation")).andReturn("-10");
		EasyMock.expect(request.getParameter("maxRotation")).andReturn("10");
		EasyMock.expect(request.getParameter("collageWidth")).andReturn("600");
		EasyMock.expect(request.getParameter("collageHeight")).andReturn("800");
		EasyMock.expect(request.getParameter("filter")).andReturn("none");
		EasyMock.expect(request.getParameter("browserWidth")).andReturn("800");
		EasyMock.expect(request.getParameter("browserHeight")).andReturn("800");
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
			System.out.println("FAIL: testServletInitialState");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testServletInitialState");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testServletInitialState");
	}

	@Test
	public void testServletNonInitialState() {
		// simulates the first call to the servlet, before establishment of session
		EasyMock.expect(request.getSession(false)).andReturn(session);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(collageManager).times(2);
		EasyMock.expect(request.getParameter("searchText")).andReturn("whales");
		EasyMock.expect(request.getParameter("shapeText")).andReturn("ocean3");
		EasyMock.expect(request.getParameter("collageBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("collageBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("photoBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("photoBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("minRotation")).andReturn("-10");
		EasyMock.expect(request.getParameter("maxRotation")).andReturn("10");
		EasyMock.expect(request.getParameter("collageWidth")).andReturn("600");
		EasyMock.expect(request.getParameter("collageHeight")).andReturn("800");
		EasyMock.expect(request.getParameter("filter")).andReturn("none");
		EasyMock.expect(request.getParameter("browserWidth")).andReturn("800");
		EasyMock.expect(request.getParameter("browserHeight")).andReturn("800");
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
			System.out.println("FAIL: testServletNonInitialState");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testServletNonInitialState");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testServletNonInitialState");
	}

	@Test
	public void testServletBadQuery() {
		// simulates the first call to the servlet, before establishment of session,
		// with a bad query
		EasyMock.expect(request.getSession(false)).andReturn(null);
		EasyMock.expect(request.getSession(true)).andReturn(session).times(2);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(null);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(collageManager);
		EasyMock.expect(request.getParameter("searchText")).andReturn("whales");
		EasyMock.expect(request.getParameter("shapeText")).andReturn("ocean3");
		EasyMock.expect(request.getParameter("collageBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("collageBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("photoBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("photoBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("minRotation")).andReturn("-10");
		EasyMock.expect(request.getParameter("maxRotation")).andReturn("10");
		EasyMock.expect(request.getParameter("collageWidth")).andReturn("600");
		EasyMock.expect(request.getParameter("collageHeight")).andReturn("800");
		EasyMock.expect(request.getParameter("filter")).andReturn("none");
		EasyMock.expect(request.getParameter("browserWidth")).andReturn("800");
		EasyMock.expect(request.getParameter("browserHeight")).andReturn("800");
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
			System.out.println("FAIL: testServletBadQuery");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testServletBadQuery");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testServletBadQuery");
	}

	@Test
	public void testServletNullQuery() {
		// simulates the first call to the servlet, before establishment of session,
		// with a null query
		EasyMock.expect(request.getSession(false)).andReturn(null);
		EasyMock.expect(request.getSession(true)).andReturn(session).times(2);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(null);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(collageManager);
		EasyMock.expect(request.getParameter("searchText")).andReturn("whales");
		EasyMock.expect(request.getParameter("shapeText")).andReturn("ocean3");
		EasyMock.expect(request.getParameter("collageBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("collageBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("photoBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("photoBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("minRotation")).andReturn("-10");
		EasyMock.expect(request.getParameter("maxRotation")).andReturn("10");
		EasyMock.expect(request.getParameter("collageWidth")).andReturn("600");
		EasyMock.expect(request.getParameter("collageHeight")).andReturn("800");
		EasyMock.expect(request.getParameter("filter")).andReturn("none");
		EasyMock.expect(request.getParameter("browserWidth")).andReturn("800");
		EasyMock.expect(request.getParameter("browserHeight")).andReturn("800");
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
			System.out.println("FAIL: testServletNullQuery");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testServletNullQuery");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testServletNullQuery");
	}

	@Test
	public void testServletEmptyQuery() {
		// simulates the first call to the servlet, before establishment of session,
		// with an empty query
		EasyMock.expect(request.getSession(false)).andReturn(null);
		EasyMock.expect(request.getSession(true)).andReturn(session).times(2);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(null);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(collageManager);
		EasyMock.expect(request.getParameter("searchText")).andReturn("whales");
		EasyMock.expect(request.getParameter("shapeText")).andReturn("ocean3");
		EasyMock.expect(request.getParameter("collageBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("collageBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("photoBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("photoBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("minRotation")).andReturn("-10");
		EasyMock.expect(request.getParameter("maxRotation")).andReturn("10");
		EasyMock.expect(request.getParameter("collageWidth")).andReturn("600");
		EasyMock.expect(request.getParameter("collageHeight")).andReturn("800");
		EasyMock.expect(request.getParameter("filter")).andReturn("none");
		EasyMock.expect(request.getParameter("browserWidth")).andReturn("800");
		EasyMock.expect(request.getParameter("browserHeight")).andReturn("800");
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
			System.out.println("FAIL: testServletEmptyQuery");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testServletEmptyQuery");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testServletEmptyQuery");
	}

	@Test
	public void testCollageBuildingFailed() {
		// simulates the first call to the servlet, before establishment of session,
		// with an empty query
		EasyMock.expect(request.getSession(false)).andReturn(null);
		EasyMock.expect(request.getSession(true)).andReturn(session).times(2);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(null);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(collageManager);
		EasyMock.expect(request.getParameter("searchText")).andReturn(
				"lkasdjf;laksjdfoa;sidfjaop;sidfja;osdikfja;osidfja;osdijf;aolsidjf;aosidfj;aosidjfa;lsdkfja;lskdfjasdf");
		EasyMock.expect(request.getParameter("shapeText")).andReturn("ocean3");
		EasyMock.expect(request.getParameter("collageBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("collageBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("photoBorderWidth")).andReturn("1");
		EasyMock.expect(request.getParameter("photoBorderColor")).andReturn("red");
		EasyMock.expect(request.getParameter("minRotation")).andReturn("-10");
		EasyMock.expect(request.getParameter("maxRotation")).andReturn("10");
		EasyMock.expect(request.getParameter("collageWidth")).andReturn("600");
		EasyMock.expect(request.getParameter("collageHeight")).andReturn("800");
		EasyMock.expect(request.getParameter("filter")).andReturn("none");
		EasyMock.expect(request.getParameter("browserWidth")).andReturn("800");
		EasyMock.expect(request.getParameter("browserHeight")).andReturn("800");
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
			System.out.println("FAIL: testCollageBuildingFailed");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testCollageBuildingFailed");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testCollageBuildingFailed");
	}

}
