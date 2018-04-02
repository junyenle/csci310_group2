package Test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.junit.Test;

import classes.CollageBuilder;
import classes.ImageSourcer;



public class JUnitTest {
	// Test for CollageBuilder Constructor
	@Test
	public void testCollageBuilderConstructor() {
		CollageBuilder collageBuilder = new CollageBuilder(800, 600);
		assertNotNull(collageBuilder);
	}

	
	// Test for buildCollage method in CollageBuilder class
	@Test
	public void testBuildCollage() {
		CollageBuilder collageBuilder = new CollageBuilder(800, 600);
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
		CollageBuilder collageBuilder = new CollageBuilder(1440, 900);
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
		CollageBuilder collageBuilder = new CollageBuilder(800, 600);
		BufferedImage src = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		int srcHeight = src.getHeight();
		int srcWidth = src.getWidth();
		BufferedImage rotatedImage = collageBuilder.rotateImage(src, 30);
		assertTrue(srcHeight != rotatedImage.getHeight());
		assertTrue(srcWidth != rotatedImage.getWidth());
	}
}
