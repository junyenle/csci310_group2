package whitebox;

import org.junit.Before;
import org.junit.Test;

import classes.CollageBuilder;
import classes.CollageOptions;
import classes.ImageSourcer;

public class CollageBuilderTests {

	CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 1, 1, "dummy");
	CollageBuilder builder = new CollageBuilder(options);
	ImageSourcer sourcer = new ImageSourcer("whales", 30);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBuildCollageWide() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 1000, 10, "dummy");
		CollageBuilder builder = new CollageBuilder(options);
		builder.buildCollage(sourcer, "HE LLO");
		System.out.println("PASS: testBuildCollageWide");
	}

	@Test
	public void testBuildCollageHigh() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 100, 1000, "dummy");
		CollageBuilder builder = new CollageBuilder(options);
		builder.buildCollage(sourcer, "HE LLO");
		System.out.println("PASS: testBuildCollageHigh");
	}

}
