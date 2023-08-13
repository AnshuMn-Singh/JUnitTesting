package mathutils;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class MethodOrderByIndexTest {
    @Order(1)
    @Test
    void TestA(){
        System.out.println("Running test A");
    }

    @Order(2)
    @Test
    void TestB(){
        System.out.println("Running test B");
    }

    @Order(3)
    @Test
    void TestC(){
        System.out.println("Running test C");
    }

    @Order(4)
    @Test
    void TestD(){
        System.out.println("Running test D");
    }
}
