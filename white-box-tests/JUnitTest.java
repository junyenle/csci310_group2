package test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.junit.Test;

import classes.CollageBuilder;
import classes.ImageSourcer;
import classes.CollageOptions;


public class JUnitTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	// Test for CollageOptions Constructor
	@Test
	public void testCollageOptionsConstructor() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		assertNotNull(collageOptions);
	}
	
	// Test for CollageOptions getCollageBorderWidth() 
	@Test
	public void testGetCollageBorderWidth() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		assertTrue(collageOptions.getCollageBorderWidth() == 3);
	}
	
	// Test for CollageOptions getCollageBorderColor() 
	@Test
	public void testGetCollageBorderColor() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		assertTrue(collageOptions.getCollageBorderColor().equalsIgnoreCase("white"));
	}
	
	// Test for CollageOptions getPhotoBorderWidth() 
	@Test
	public void testGetPhotoBorderWidth() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		assertTrue(collageOptions.getPhotoBorderWidth() == 3);
	}
	
	// Test for CollageOptions getPhotoBorderColor() 
	@Test
	public void testGetPhotoBorderColor() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		assertTrue(collageOptions.getPhotoBorderColor().equalsIgnoreCase("white"));
	}
	
	// Test for CollageOptions getMinRotation() 
	@Test
	public void testGetMinRotation() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		assertTrue(collageOptions.getMinRotation() == 0);
	}
	
	// Test for CollageOptions getMaxRotation() 
	@Test
	public void testGetMaxRotation() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		assertTrue(collageOptions.getMaxRotation() == 90);
	}
	
	// Test for CollageOptions getCollageWidth() 
	@Test
	public void testGetCollageWidth() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		assertTrue(collageOptions.getCollageWidth() == 800);
	}
	
	// Test for CollageOptions getCollageHeight() 
	@Test
	public void testGetCollageHeight() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		assertTrue(collageOptions.getCollageHeight() == 600);
	}
	
	// Test for CollageOptions getFilter() 
	@Test
	public void testGetFilter() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		assertTrue(collageOptions.getFilter().equalsIgnoreCase("filter"));
	}
	
	// Test for CollageBuilder Constructor
	@Test
	public void testCollageBuilderConstructor() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		CollageBuilder collageBuilder = new CollageBuilder(collageOptions, 800, 600);
		assertNotNull(collageBuilder);
	}

	
	// Test for buildCollage method in CollageBuilder class
	@Test
	public void testBuildCollage() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		CollageBuilder collageBuilder = new CollageBuilder(collageOptions, 800, 600);
		ImageSourcer s = new ImageSourcer("cat", 30);
		BufferedImage collage = collageBuilder.buildCollage(s, "SH PE");
		assertNotNull(collage);
		System.out.println(collage.getWidth());
		System.out.println(collage.getHeight());
		assertTrue(collage.getWidth() == 800);
		assertTrue(collage.getHeight() == 600);
	}
	
	// Test for buildCollage method in CollageBuilder class
	@Test
	public void testBuildCollageBrowser() {
		// Using browser width and height 1440 and 900
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		CollageBuilder collageBuilder = new CollageBuilder(collageOptions, 1440, 900);
		ImageSourcer s = new ImageSourcer("cat", 30);
		BufferedImage collage = collageBuilder.buildCollage(s, "S");
		assertNotNull(collage);
		// Max of 0.7*1440 = 1007 and 800
		assertTrue(collage.getWidth() == 1007);
		// Max of 0.5*900 = 450 and 600
		assertTrue(collage.getHeight() == 600);
	}
	
	
	
	// Test for rotateImage method in CollageBuilder class
	@Test
	public void testRotateImage() {
		CollageOptions collageOptions = new CollageOptions(3, "white", 3, "white", 0, 90, 800, 600, "filter");
		CollageBuilder collageBuilder = new CollageBuilder(collageOptions, 800, 600);
		BufferedImage src = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		int srcHeight = src.getHeight();
		int srcWidth = src.getWidth();
		BufferedImage rotatedImage = collageBuilder.rotateImage(src, 30);
		assertTrue(srcHeight != rotatedImage.getHeight());
		assertTrue(srcWidth != rotatedImage.getWidth());
	}
	
	//Test for security
	@Test
	public void testLogin() {
//		Security s = new Security();
		boolean loginT = Security.authenticate("jun", "july");
		String messageT = outContent.toString();
		
		boolean loginF = Security.authenticate("jun", "jacob");
		String messageF = outContent.toString();
		
		assertTrue(loginT);
		assertEquals("Successfully logged in user jun", messageT);
		
		assertFalse(loginF);
		assertEquals("Unable to log in user jun", messageF);
	}
	
//	register is private, so this might not work
//	@Test
//	public void testRegister() {
//		boolean regT = Security.registerUser("abc", "123");
//		String messageT = outContent.toString();
//		
//		boolean regF = Security.registerUser("jun", "123");
//		String messageF = outContent.toString();
//		
//		assertTrue(regT);
//		assertEquals("User pedro registered", messageT);
//		
//		assertFalse(regF);
//		assertEquals("User jun already exists!", messageF);
//	}
	
	//Tests for Image Sourcer
	@Test
	public void testImageSourcer() {
		ImageSourcer s = new ImageSourcer("sharks", 3);
		BufferedImage b = s.getImage();
		assertNotNull(b);
		
		ImageSourcer s2 = new ImageSourcer("afheodi  hgouvrehivn oreg", 3);
		BufferedImage b2 = s2.getImage();
		assertNull(b2);
	}
	
}
