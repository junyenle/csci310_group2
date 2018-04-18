package whitebox;

import org.junit.Before;
import org.junit.Test;

import classes.CollageOptions;

public class CollageOptionsTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAllOptions() {
		CollageOptions options = new CollageOptions(1, "red", 1, "red", 1, 1, 1, 1, "dummy");
		options.getCollageBorderWidth();
		options.getCollageBorderColor();
		options.getPhotoBorderWidth();
		options.getPhotoBorderColor();
		options.getMinRotation();
		options.getMaxRotation();
		options.getCollageWidth();
		options.getCollageHeight();
		options.getFilter();

	}

}
