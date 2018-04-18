package whitebox;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CollageBuilderServletTests.class, CollageBuilderTests.class, CollageManagerTests.class,
		CollageOptionsTests.class, ImageSourcerTests.class, LoginServletTests.class, SecurityTests.class, SaveCollageServletTests.class })
public class AllTests {

}
