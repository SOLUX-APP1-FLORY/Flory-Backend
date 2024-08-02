package flory.FloryServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class FloryServerApplicationTests {

	@Autowired
	private TestService testService;

	@BeforeEach
	public void before(){
		System.out.println("Test Before");
	}

	@AfterEach
	public void after(){
		System.out.println("Test After");
	}

	@Test
	@DisplayName("두 수가 일치해야 성공")
	public void test() throws Exception {
		// Given
		int number = 0;
		System.out.println("Given");

		// When
		int generateNumber = testService.generate();
		System.out.println("When");

		// Then
		assertThat(generateNumber).isEqualTo(number);
		System.out.println("Then");
	}

}
