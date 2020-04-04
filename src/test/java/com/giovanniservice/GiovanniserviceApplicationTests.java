package com.giovanniservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/test-application.yml")
class GiovanniserviceApplicationTests {

	@Test
	void contextLoads() {
	}

}
