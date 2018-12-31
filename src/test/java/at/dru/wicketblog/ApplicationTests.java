package at.dru.wicketblog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WebApplication.class)
@WebAppConfiguration
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
