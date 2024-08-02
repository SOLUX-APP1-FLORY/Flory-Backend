package flory.FloryServer;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional // 각각의 테스트 메서드에 대해 트랜잭션을 시작하고, 테스트가 종료되면 롤백
class ServiceTest {

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

//    @Test
//    @DisplayName("두 수가 일치하지 않아야 성공")
//    public void test2() throws Exception {
//        // Given
//        int number = 0;
//        System.out.println("Given");
//
//        // When
//        int generateNumber = testService.generate();
//        System.out.println("When");
//
//        // Then
//        assertThat(generateNumber).isNotEqualTo(number);  // `generateNumber`가 `0`이 아닌 값을 기대
//        System.out.println("Then");
//    }

}
