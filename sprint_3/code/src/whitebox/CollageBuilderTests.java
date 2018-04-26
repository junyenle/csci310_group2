package whitebox;

import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import classes.CollageBuilder;
import classes.CollageOptions;
import classes.ImageSourcer;

public class CollageBuilderTests {

	ImageSourcer sourcer = new ImageSourcer("whales", 30);

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testBuildCollageSepia() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 1000, 10, "sepia");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}

	@Test
	public void testBuildCollageBlackWhite() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 1000, 10, "blacknwhite");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}

	@Test
	public void testBuildCollageGreyscale() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 1000, 10, "greyscale");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}
	
	@Test
	public void testBuildCollageBadFilter() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 1000, 10, "invalid");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}
	
	@Test
	public void testBuildCollageRotation() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", -45, 45, 1000, 10, "invalid");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}
	
	@Test
	public void testBuildCollageNoRotation() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 0, 0, 1000, 10, "invalid");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}	
	
	@Test
	public void testBuildCollageBadRotationBothNeg() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", -5, -10, 1000, 10, "invalid");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}
	
	@Test
	public void testBuildCollageBadRotationBothPos() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 10, 5, 1000, 10, "invalid");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}	
	
	@Test
	public void testBuildCollageBadRotationOnePosOneNeg() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 10, -10, 1000, 10, "invalid");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}
	
	@Test
	public void testBuildCollageNoFilter() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 1000, 10, "");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}
	
	@Test
	public void testBuildCollageCollageBordersNoPhotoBorders() {
		CollageOptions options = new CollageOptions(2, "green", 0, "green", 1, 1, 1000, 10, "");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}	
	
	@Test
	public void testBuildCollageNoCollageBordersPhotoBorders() {
		CollageOptions options = new CollageOptions(0, "green", 2, "green", 1, 1, 1000, 10, "");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}	
	
	@Test
	public void testBuildCollageInvalidColor() {
		CollageOptions options = new CollageOptions(1, "nonexistentcolor", 1, "nonexistentcolor", 1, 1, 1000, 10, "");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}
	
	@Test
	public void testBuildCollageWide() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 1000, 10, "");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageWide");
	}

	@Test
	public void testBuildCollageHigh() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 100, 1000, "");
		CollageBuilder builder = new CollageBuilder(options);
		BufferedImage image = builder.buildCollage(sourcer, "HE LLO");
		if(image == null)
		{
			fail("collage not built");
		}
		System.out.println("PASS: testBuildCollageHigh");
	}

}
