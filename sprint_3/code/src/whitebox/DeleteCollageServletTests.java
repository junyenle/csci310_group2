package whitebox;

import static org.easymock.EasyMock.createNiceMock;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
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

import classes.CollageManager;
import classes.Security;
import servlet.DeleteCollageServlet;

public class DeleteCollageServletTests {
	private DeleteCollageServlet servlet = new DeleteCollageServlet();
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	private PrintWriter writer;
	private CollageManager manager = new CollageManager();

	@Before
	public void setUp() throws Exception {
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
	public void testDeleteNoSession() {
		BufferedImage image = new BufferedImage(1, 1, 1);
		manager.insertCollage("test collage", image);
		try {
			EasyMock.expect(request.getSession(false)).andReturn(null);
			EasyMock.expect(session.getAttribute("collageManager")).andReturn(manager).times(2);
			EasyMock.expect(request.getParameter("clickedIndex")).andReturn("0");
			EasyMock.expect(session.getAttribute("username")).andReturn("test user");
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
			System.out.println("FAIL: testDeleteNoSession");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testDeleteNoSession");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testDeleteNoSession");
	}

	@Test
	public void testDeleteNoManager() {
		BufferedImage image = new BufferedImage(1, 1, 1);
		manager.insertCollage("test collage", image);
		try {
			EasyMock.expect(request.getSession(false)).andReturn(session).times(2);
			EasyMock.expect(session.getAttribute("collageManager")).andReturn(null).times(2);
			EasyMock.expect(request.getParameter("clickedIndex")).andReturn("0");
			EasyMock.expect(session.getAttribute("username")).andReturn("test user");
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
			System.out.println("FAIL: testDeleteNoManager");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testDeleteNoManager");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testDeleteNoManager");
	}
	
	@Test
	public void testDeleteOneCollage() {
		BufferedImage image = new BufferedImage(1, 1, 1);
		manager.insertCollage("test collage", image);
		try {
			EasyMock.expect(request.getSession(false)).andReturn(session).times(2);
			EasyMock.expect(session.getAttribute("collageManager")).andReturn(manager).times(2);
			EasyMock.expect(request.getParameter("clickedIndex")).andReturn("0");
			EasyMock.expect(session.getAttribute("username")).andReturn("test user");
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
			System.out.println("FAIL: testDeleteOneCollage");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testDeleteOneCollage");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testDeleteOneCollage");
	}

	@Test
	public void testDeleteMultipleCollages() {
		BufferedImage image = new BufferedImage(1, 1, 1);
		manager.insertCollage("test collage", image);
		manager.insertCollage("test collage 2", image);
		try {
			EasyMock.expect(request.getSession(false)).andReturn(session).times(2);
			EasyMock.expect(session.getAttribute("collageManager")).andReturn(manager).times(2);
			EasyMock.expect(request.getParameter("clickedIndex")).andReturn("0");
			EasyMock.expect(session.getAttribute("username")).andReturn("test user");
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
			System.out.println("FAIL: testDeleteMultipleCollages");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testDeleteMultipleCollages");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testDeleteMultipleCollages");
	}

	@Test
	public void testDeleteZeroCollages() {
		try {
			EasyMock.expect(request.getSession(false)).andReturn(session).times(2);
			EasyMock.expect(session.getAttribute("collageManager")).andReturn(manager).times(2);
			EasyMock.expect(request.getParameter("clickedIndex")).andReturn("0");
			EasyMock.expect(session.getAttribute("username")).andReturn("test user");
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
			System.out.println("FAIL: testDeleteZeroCollages");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testDeleteZeroCollages");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testDeleteZeroCollages");
	}

}
