package whitebox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import classes.CollageManager;

public class CollageManagerTests {

	// constant parameters
	private static int COLLAGE_HEIGHT = 600; // width of test (dummy) collage
	private static int COLLAGE_WIDTH = 800; // width of test (dummy) collage
	private CollageManager collageManager;

	@Before
	public void setUp() throws Exception {
		collageManager = new CollageManager(); // generate fresh collage manager for each test
	}

	@Test
	public void testInitiallyEmpty() {
		// checks that collage manager is initially empty
		if (collageManager.getCollages().size() != 0 || collageManager.getCollageTitles().size() != 0) {
			System.out.println("FAIL: testInitiallyEmpty");
			fail("Collage manager not initially empty.");
		}
		System.out.println("PASS: testInitiallyEmpty");
	}

	@Test
	public void testSaveCollage() {
		BufferedImage image = new BufferedImage(1, 1, 1);
		collageManager.insertCollage("test collage", image);
		collageManager.saveCollage();
		if (collageManager.getSavedCollages().isEmpty() || collageManager.getSavedCollageTitles().isEmpty()) {
			System.out.println("FAIL: testSaveCollage");
			fail("Save Collage fail.");
		}
		assertTrue(collageManager.getSavedCollageTitles().get(0).equals("test collage"));
		System.out.println("PASS: testSaveCollage");
	}

	@Test
	public void testSaveCollageNoCollages() {
		collageManager.saveCollage();
		if (collageManager.getSavedCollages().isEmpty() && collageManager.getSavedCollageTitles().isEmpty()) {
			System.out.println("PASS: testSaveCollage");
			return;
		}
		System.out.println("FAIL: testSaveCollage");
		fail();
	}

	@Test
	public void testSaveSameCollage() {
		BufferedImage image = new BufferedImage(100, 100, 1);
		Graphics2D graphics = image.createGraphics();
		graphics.setPaint(new Color(100, 0, 0));
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

		BufferedImage image2 = new BufferedImage(100, 100, 1);
		Graphics2D graphics2 = image2.createGraphics();
		graphics2.setPaint(new Color(100, 0, 0));
		graphics2.fillRect(0, 0, image2.getWidth(), image2.getHeight());

		collageManager.insertCollage("test collage", image);
		collageManager.saveCollage();
		collageManager.insertCollage("test collage", image2);
		collageManager.saveCollage();

		if (collageManager.getSavedCollages().isEmpty() || collageManager.getSavedCollageTitles().isEmpty()) {
			System.out.println("FAIL: testSaveSameCollage");
			fail("Save Same Collage fail.");
		}
		assertTrue(collageManager.getSavedCollageTitles().get(0).equals("test collage"));
		assertTrue(collageManager.getSavedCollages().size() == 1);
		assertTrue(collageManager.getSavedCollageTitles().size() == 1);
		System.out.println("PASS: testSaveSameCollage");
	}

	@Test
	public void testSaveMultipleDifferentCollages() {
		BufferedImage image = new BufferedImage(100, 100, 1);
		Graphics2D graphics = image.createGraphics();
		graphics.setPaint(new Color(100, 0, 0));
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

		BufferedImage image2 = new BufferedImage(100, 100, 1);
		Graphics2D graphics2 = image2.createGraphics();
		graphics2.setPaint(new Color(0, 100, 0));
		graphics2.fillRect(0, 0, image2.getWidth(), image2.getHeight());

		BufferedImage image3 = new BufferedImage(100, 200, 1);
		Graphics2D graphics3 = image3.createGraphics();
		graphics3.setPaint(new Color(0, 100, 0));
		graphics3.fillRect(0, 0, image3.getWidth(), image3.getHeight());

		collageManager.insertCollage("test", image);
		collageManager.saveCollage();
		collageManager.insertCollage("test", image2);
		collageManager.saveCollage();
		collageManager.insertCollage("test", image3);
		collageManager.saveCollage();

		if (collageManager.getSavedCollages().size() == 3 && collageManager.getSavedCollageTitles().size() == 3) {
			System.out.println("PASS: testSaveMultipleDifferentCollages");
			return;
		}
		fail("FAIL: testSaveMultipleDifferentCollages");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testInsertFirstCollage() {
		// inserts one collage and one title, checks if the stored collage and title
		// match inserted
		BufferedImage dummyCollage = new BufferedImage(COLLAGE_WIDTH, COLLAGE_HEIGHT, 1);
		String dummyTitle = "testTitle";
		collageManager.insertCollage(dummyTitle, dummyCollage); // insert the dummy titles and collages to manager

		try {
			Field collageVector = collageManager.getClass().getDeclaredField("collages");
			collageVector.setAccessible(true);
			Field titlesVector = collageManager.getClass().getDeclaredField("collageTitles");
			titlesVector.setAccessible(true);

			// check what was actually inserted
			String resultTitle = ((Vector<String>) titlesVector.get(collageManager)).get(0);
			BufferedImage resultCollage = ((Vector<BufferedImage>) collageVector.get(collageManager)).get(0);

			// check that title matches what was inserted
			String expectedTitle = dummyTitle;
			assertEquals("FAIL: testInsertFirstCollage", resultTitle, expectedTitle);

			// check that inserted vector is indeed a COLLAGE_WIDTH x COLLAGE_HEIGHT
			// buffered image
			if (resultCollage == null) {
				System.out.println("FAIL: testInsertFirstCollage");
				fail("Inserted collage was null.");
			}

			try {
				if (resultCollage.getWidth() != COLLAGE_WIDTH || resultCollage.getHeight() != COLLAGE_HEIGHT) {
					System.out.println("FAIL: testInsertFirstCollage");
					fail("Inserted collage dimensions were wrong.");
				}
			} catch (Exception e) {
				System.out.println("FAIL: testInsertFirstCollage");
				fail("Inserted collage was not a buffered image.");
			}

		} catch (Exception e) {
			System.out.println("FAIL: testInsertFirstCollage");
			fail("Inconclusive: test threw exception.");
		}

		System.out.println("PASS: testInsertFirstCollage");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testInsertAnotherCollage() {
		// inserts two collages and two titles, checks if the second stored collage and
		// title match inserted
		// this also ensures that collages and titles are being added in order
		BufferedImage dummyCollage = new BufferedImage(COLLAGE_WIDTH, COLLAGE_HEIGHT, 1);
		BufferedImage secondDummyCollage = new BufferedImage(2 * COLLAGE_WIDTH, 2 * COLLAGE_HEIGHT, 1);
		String dummyTitle = "testTitle";
		String secondDummyTitle = "secondTestTitle";
		collageManager.insertCollage(dummyTitle, dummyCollage); // insert the dummy titles and collages to manager
		collageManager.insertCollage(secondDummyTitle, secondDummyCollage);

		try {
			Field collageVector = collageManager.getClass().getDeclaredField("collages");
			collageVector.setAccessible(true);
			Field titlesVector = collageManager.getClass().getDeclaredField("collageTitles");
			titlesVector.setAccessible(true);

			// check what was actually inserted
			String resultTitle = ((Vector<String>) titlesVector.get(collageManager)).get(1);
			BufferedImage resultCollage = ((Vector<BufferedImage>) collageVector.get(collageManager)).get(1);

			// check that title matches what was inserted
			String expectedTitle = secondDummyTitle; // for clarity
			assertEquals("FAIL: testInsertAnotherCollage", resultTitle, expectedTitle);

			// check that inserted vector is indeed a COLLAGE_WIDTH x COLLAGE_HEIGHT
			// buffered image
			if (resultCollage == null) {
				System.out.println("FAIL: testInsertAnotherCollage");
				fail("Inserted collage was null.");
			}

			try {
				if (resultCollage.getWidth() != 2 * COLLAGE_WIDTH || resultCollage.getHeight() != 2 * COLLAGE_HEIGHT) {
					System.out.println("FAIL: testInsertAnotherCollage");
					fail("Inserted collage dimensions were wrong.");
				}
			} catch (Exception e) {
				System.out.println("FAIL: testInsertAnotherCollage");
				fail("Inserted collage was not a buffered image.");
			}

		} catch (Exception e) {
			System.out.println("FAIL: testInsertAnotherCollage");
			fail("Inconclusive: test threw exception.");
		}

		System.out.println("PASS: testInsertAnotherCollage");
	}

	@Test
	public void testGetCollageTitlesNoElements() {
		// tests the getter function when no titles exist

		// create test vector with 0 elements
		Vector<String> testTitles = new Vector<String>();

		// setting the collage manager's title vector
		Field collageTitles;
		try {
			collageTitles = collageManager.getClass().getDeclaredField("collageTitles");
			collageTitles.setAccessible(true);
			collageTitles.set(collageManager, testTitles);
		} catch (Exception e) {
			System.out.println("FAIL: testGetCollageTitlesNoElements");
			fail("Inconclusive: test threw exception.");
		}

		// calling the getter function
		Vector<String> retrievedTitles = collageManager.getCollageTitles();

		// check if null
		if (retrievedTitles == null) {

			System.out.println("FAIL: testGetCollageTitlesNoElements");
			fail("Getter function returned null vector.");
		}

		// check if size is 0
		assertEquals("FAIL: testGetCollageTitlesNoElements", retrievedTitles.size(), 0);

		System.out.println("PASS: testGetCollageTitlesNoElements");

	}

	@Test
	public void testGetCollageTitlesElementsExist() {
		// tests the getter function when titles exist

		// create test vector with 10 elements
		Vector<String> testTitles = new Vector<String>();
		for (int i = 0; i < 10; i++) {
			testTitles.add(String.valueOf(i)); // values will be "0" to "9"
		}

		// setting the collage manager's title vector
		Field collageTitles;
		try {
			collageTitles = collageManager.getClass().getDeclaredField("collageTitles");
			collageTitles.setAccessible(true);
			collageTitles.set(collageManager, testTitles);
		} catch (Exception e) {
			System.out.println("FAIL: testGetCollageTitlesElementsExist");
			fail("Inconclusive: test threw exception.");
		}

		// calling the getter function
		Vector<String> retrievedTitles = collageManager.getCollageTitles();

		// check if null
		if (retrievedTitles == null) {

			System.out.println("FAIL: testGetCollageTitlesElementsExist");
			fail("Getter function returned null vector.");
		}

		// check if right nubmer of elements
		assertEquals("FAIL: testGetCollageTitlesElementsExist", retrievedTitles.size(), 10);

		// check if equal
		for (int i = 0; i < retrievedTitles.size(); i++) {
			assertEquals("FAIL: testGetCollageTitlesElementsExist", retrievedTitles.get(i), testTitles.get(i));
		}

		System.out.println("PASS: testGetCollageTitlesElementsExist");
	}

	@Test
	public void testGetCollagesNoElements() {
		// tests the getter function when no collages exist

		// create test vector with 0 elements
		Vector<BufferedImage> testCollages = new Vector<BufferedImage>();

		// setting the collage manager's collages vector
		Field collages;
		try {
			collages = collageManager.getClass().getDeclaredField("collages");
			collages.setAccessible(true);
			collages.set(collageManager, testCollages);
		} catch (Exception e) {
			System.out.println("FAIL: testGetCollagesNoElements");
			fail("Inconclusive: test threw exception.");
		}

		// calling the getter function
		Vector<BufferedImage> retrievedCollages = collageManager.getCollages();

		// check if null
		if (retrievedCollages == null) {

			System.out.println("FAIL: testGetCollagesNoElements");
			fail("Getter function returned null vector.");
		}

		// check if size is 0
		assertEquals("FAIL: testGetCollagesNoElements", retrievedCollages.size(), 0);

		System.out.println("PASS: testGetCollagesNoElements");

	}

	@Test
	public void testGetCollagesElementsExist() {
		// tests the getter function when collages exist

		// create test vector with 10 elements
		Vector<BufferedImage> testCollages = new Vector<BufferedImage>();
		for (int i = 0; i < 10; i++) {
			BufferedImage testImage = new BufferedImage(800, 600, 1);
			testCollages.add(testImage);
		}

		// setting the collage manager's collages vector
		Field collages;
		try {
			collages = collageManager.getClass().getDeclaredField("collages");
			collages.setAccessible(true);
			collages.set(collageManager, testCollages);
		} catch (Exception e) {
			System.out.println("FAIL: testGetCollagesElementsExist");
			fail("Inconclusive: test threw exception.");
		}

		// calling the getter function
		Vector<BufferedImage> retrievedCollages = collageManager.getCollages();

		// check if null
		if (retrievedCollages == null) {
			System.out.println("FAIL: testGetCollagesElementsExist");
			fail("Getter function returned null vector.");
		}

		// check if size is 10
		assertEquals("FAIL: testGetCollagesElementsExist", retrievedCollages.size(), 10);

		// check that each of the images is still an 800x600 buffered image
		for (int i = 0; i < retrievedCollages.size(); i++) {
			assertEquals("FAIL: testGetCollagesElementsExist", retrievedCollages.get(i).getWidth(), 800);
			assertEquals("FAIL: testGetCollagesElementsExist", retrievedCollages.get(i).getHeight(), 600);
		}

		System.out.println("PASS: testGetCollagesElementsExist");

	}

}
