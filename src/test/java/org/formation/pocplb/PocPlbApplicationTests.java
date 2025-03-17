package org.formation.pocplb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = PlbsiAiApplication.class)
@ActiveProfiles("test")
class PocPlbApplicationTests {

	@Test
	void contextLoads() {
		// Just testing that the application context loads successfully
	}

}
