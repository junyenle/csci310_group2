package whitebox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import classes.ImageSourcer;

public class ImageSourcerTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetImage() {
		ImageSourcer im = new ImageSourcer("whales", 1);
		for (int i = 0; i < 50; i++) {
			im.getImage();
		}
	}

	@Test
	public void testImageSourcerSufficientImages() {
		// tests image sourcer's ability to get images if enough exist
		ImageSourcer im = new ImageSourcer("whales", 30);
		BufferedImage image = im.getImage();
		if (image == null) {
			fail("FAIL: testImageSourcerSufficientImages");
		}
		System.out.println("PASS: testImageSourcerSufficientImages");
	}

	@Test
	public void testImageSourcerInufficientImages() {
		// tests image sourcer's ability to return null if < 30 found
		try {
			ImageSourcer im = new ImageSourcer(
					"asdfasdfasdkfjasl;dfkja;lsdkfja;sdlfkjas;ldfkja;sdlkfja;sdlkfjas;slfkdjas;dlfkjads", 30);
			if (im.isValid()) {
				fail("FAIL: testImageSourcerInsufficientImages");
			}
			System.out.println("PASS: testImageSourcerInufficientImages");
		} catch (Exception e) {
			System.out.println("PASS: testImageSourcerInufficientImages");
			return;
		}
	}

	@Test
	public void testImageSourcerInvalidApiKey() {
		// tests image sourcer's invalid Google connection exception catching by passing
		// a bad api key
		try {
			// access API_KEY constant
			ImageSourcer im = new ImageSourcer("whales", 30);
			Field apiKey = im.getClass().getDeclaredField("API_KEY");
			apiKey.setAccessible(true);

			// change API_KEY to non-final
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(apiKey, apiKey.getModifiers() & ~Modifier.FINAL);

			// change API_KEY to a dummy
			apiKey.set(im, "dummy_key");

			// ensure that API_KEY is modified
			assertEquals("FAIL: testImageSourcerInvalidApiKey", apiKey.get(im), "dummy_key");

			// image search should return null
			Vector<String> returnedURLs = im.p_getImages("whales", 30);
			assertEquals("FAIL: testImageSourcerInvalidApiKey", returnedURLs, null);

		} catch (IllegalAccessException e) {
			System.out.println("FAIL: testImageSourcerInvalidApiKey");
			fail("Inconclusive: test threw exception.");
		} catch (NoSuchFieldException e) {
			System.out.println("FAIL: testImageSourcerInvalidApiKey");
			fail("Inconclusive: test threw exception.");
		}

		System.out.println("PASS: testImageSourcerInvalidApiKey");
	}

	@Test
	public void testImageSourcerInvalidParameters() {
		// tests image sourcer's catch-all for invalid parameters
		try {
			ImageSourcer im = new ImageSourcer("whales", 30);
			// access GOOGLE_SEARCH_LIMIT constant
			Field searchLimit = im.getClass().getDeclaredField("GOOGLE_SEARCH_LIMIT");
			searchLimit.setAccessible(true);

			// change GOOGLE_SEARCH_LIMIT to non-final
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(searchLimit, searchLimit.getModifiers() & ~Modifier.FINAL);

			// change GOOGLE_SEARCH_LIMIT to 0
			searchLimit.set(im, 0); // in attempt to throw divide by zero exception

			// ensure that GOOGLE_SEARCH_LIMIT is modified
			assertEquals("FAIL: testImageSourcerInvalidParameters", searchLimit.get(im), 0);

			// image search should return null
			Vector<String> returnedURLs = im.p_getImages("whales", 30);
			assertEquals("FAIL: testImageSourcerInvalidParameters", returnedURLs, null);

		} catch (IllegalAccessException e) {
			System.out.println("FAIL: testImageSourcerInvalidParameters");
			fail("Inconclusive: test threw exception.");
		} catch (NoSuchFieldException e) {
			System.out.println("FAIL: testImageSourcerInvalidParameters");
			fail("Inconclusive: test threw exception.");
		}
		System.out.println("PASS: testImageSourcerInvalidParameters");
	}

	@Test
	public void testImageSourcerInvalidURL() {
		// tests image sourcer's catch for invalid URLs
		try {
			ImageSourcer im = new ImageSourcer("whales", 30);
			// access API_KEY constant
			Field apiKey = im.getClass().getDeclaredField("API_KEY");
			apiKey.setAccessible(true);

			// change API_KEY to non-final
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(apiKey, apiKey.getModifiers() & ~Modifier.FINAL);

			// change API_KEY to have a snowman, creating a bad url
			apiKey.set(im, "☃"); // in attempt to throw divide by zero exception

			// ensure that GOOGLE_SEARCH_LIMIT is modified
			assertEquals("FAIL: testImageSourcerInvalidURL", apiKey.get(im), "☃");

			// image search should return null
			Vector<String> returnedURLs = im.p_getImages("whales", 30);
			assertEquals("FAIL: testImageSourcerInvalidURL", returnedURLs, null);

		} catch (IllegalAccessException e) {
			System.out.println("FAIL: testImageSourcerInvalidURL");
			fail("Inconclusive: test threw exception.");
		} catch (NoSuchFieldException e) {
			System.out.println("FAIL: testImageSourcerInvalidURL");
			fail("Inconclusive: test threw exception.");
		}
		System.out.println("PASS: testImageSourcerInvalidURL");
	}

}
