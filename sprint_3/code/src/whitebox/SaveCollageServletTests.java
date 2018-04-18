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
import servlet.SaveCollageServlet;

public class SaveCollageServletTests {
	private SaveCollageServlet servlet;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	private PrintWriter writer;
	private CollageManager manager;

	@Before
	public void setUp() throws Exception {
		servlet = new SaveCollageServlet();
		request = createNiceMock(HttpServletRequest.class);
		response = createNiceMock(HttpServletResponse.class);
		session = createNiceMock(HttpSession.class);
		writer = createNiceMock(PrintWriter.class);
		manager = new CollageManager();
	}

	@After
	public void tearDown() throws Exception {
		session.invalidate();
	}

	@Test
	public void testSaveServletNoSessionGoodSave() {
		EasyMock.expect(request.getSession(false)).andReturn(null);
		EasyMock.expect(request.getSession(true)).andReturn(session).times(2);
		manager.insertCollage("title", new BufferedImage(100, 100, 1));
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(manager).times(2);

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
			System.out.println("FAIL: testSaveServletNoSessionGoodSave");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testSaveServletNoSessionGoodSave");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testSaveServletNoSessionGoodSave");
	}

	@Test
	public void testSaveServletNoSessionBadSave() {
		EasyMock.expect(request.getSession(false)).andReturn(null);
		EasyMock.expect(request.getSession(true)).andReturn(session);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(manager).times(2);

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
			System.out.println("FAIL: testSaveServletNoSessionBadSave");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testSaveServletNoSessionBadSave");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testSaveServletNoSessionBadSave");
	}

	@Test
	public void testSaveServletYesSessionGoodSave() {
		EasyMock.expect(request.getSession(false)).andReturn(session);
		EasyMock.expect(request.getSession(true)).andReturn(session);
		manager.insertCollage("title", new BufferedImage(100, 100, 1));
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(manager).times(2);

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
			System.out.println("FAIL: testSaveServletYesSessionGoodSave");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testSaveServletYesSessionGoodSave");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testSaveServletYesSessionGoodSave");
	}

	@Test
	public void testSaveServletYesSessionBadSave() {
		EasyMock.expect(request.getSession(false)).andReturn(session);
		EasyMock.expect(request.getSession(true)).andReturn(session);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(manager).times(2);

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
			System.out.println("FAIL: testSaveServletYesSessionBadSave");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testSaveServletYesSessionBadSave");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testSaveServletYesSessionBadSave");
	}

	@Test
	public void testSaveServletYesSessionNoManager() {
		EasyMock.expect(request.getSession(false)).andReturn(session);
		EasyMock.expect(request.getSession(true)).andReturn(session);
		manager.insertCollage("title", new BufferedImage(100, 100, 1));
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(null);
		EasyMock.expect(session.getAttribute("collageManager")).andReturn(manager);

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
			System.out.println("FAIL: testSaveServletYesSessionNoManager");
			fail("Failed to complete task");
		} catch (IOException e) {
			System.out.println("FAIL: testSaveServletYesSessionNoManager");
			fail("Failed to complete task");
		}
		System.out.println("PASS: testSaveServletYesSessionNoManager");
	}

}
