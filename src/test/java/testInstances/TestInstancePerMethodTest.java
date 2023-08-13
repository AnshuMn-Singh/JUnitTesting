package testInstances;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestInstancePerMethodTest {
    StringBuffer str = new StringBuffer();

    @AfterEach
    void afterEach(){
        System.out.println("the state of test object is : " + str);
    }

    @Test
    void TestA(){
        System.out.println("Running test A");
        str.append("1");
    }

    @Test
    void TestB(){
        System.out.println("Running test B");
        str.append("2");
    }

    @Test
    void TestC(){
        System.out.println("Running test C");
        str.append("3");
    }

    @Test
    void TestD(){
        System.out.println("Running test D");
        str.append("4");
    }
}
