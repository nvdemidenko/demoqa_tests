import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SimpleJUnitTest {

    @BeforeEach
    void beforeEach(){
        System.out.println("before each");
    }

    @AfterEach
    void afterEach(){
        System.out.println("after each\n");
    }

    @Test
    void firstTest() {
        System.out.println("hello world");
        Assertions.assertTrue(3>2);
    }

    @Test
    void secondTest() {
        System.out.println("hello world");
        Assertions.assertTrue(3>2);
    }
}
