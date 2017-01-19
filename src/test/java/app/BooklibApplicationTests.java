package app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import readinglist.ReadingListApplication;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReadingListApplication.class)
@WebAppConfiguration
public class BooklibApplicationTests {

	/*It’s an empty method. But it’s sufficient for the
	purpose of verifying that the application context loads without any problems. If the
	configuration defined in ReadingListApplication is good, the test will pass. If there
	are any problems, the test will fail.*/
	@Test
	public void contextLoads() {
	}

}
