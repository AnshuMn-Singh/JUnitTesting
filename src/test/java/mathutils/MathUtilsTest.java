package mathutils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MathUtilsTest {
    MathUtils mu;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeAll
    static void beforeAllInit(){
        System.out.println("before all method working");
    }

    @AfterAll
    static void afterAllInit(){
        System.out.println("after all method working");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        mu = new MathUtils();
    }

    @AfterEach
    void cleanup(){
        System.out.println("hello");
    }

    @Test
    @DisplayName("Testing add method")
    @Tag("Math")
    void add(){
        assertEquals(2, mu.add(1, 1), "the add method should add two number");
        Assertions.assertEquals("s", "s", "b");
    }

    @Nested
    @DisplayName("add two number class")
    class AddTest{
        @Test
        @Tag("Math")
        void addPositiveValue(){
            assertEquals(10, mu.add(9, 1));
        }

            @Test
            @Tag("Math")
            void addNegativeValue(){
                int expected = 5;
                int actual = mu.add(10, -5);
                assertEquals(expected, actual, "should return " + expected + " but returned " + actual);
                assertEquals(expected, actual, () ->  "should return " + expected + " but returned " + actual);
            }
    }

    @Test
    @Tag("Math")
    void subtract(){
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());

        boolean isServerUp = false;
        assumeTrue(isServerUp);
        assertEquals(3, mu.subtract(5, 2));
    }

    @Test
    @Tag("Math")
    void multiply(){
        assertAll(
                () -> assertEquals(0, mu.multiple(1, 0)),
                () -> assertEquals(-2, mu.multiple(2, -1)),
                () -> assertEquals(10, mu.multiple(1, 10)),
                () -> assertEquals(4, mu.multiple(2, 2)),
                () -> assertEquals(10, mu.multiple(-5, -2))
        );
    }

    @RepeatedTest(3)
    void divideTest(RepetitionInfo repetitionInfo) {
        repetitionInfo.getCurrentRepetition();
        assertThrows(NullPointerException.class, () -> mu.divide(1, 0), "can't divide by zero");
    }


    @Test
    @EnabledOnOs(OS.LINUX)
    @Tag("Circle")
    void circumferenceCircle(){
        assertEquals(314.1592653589793,  mu.circumferenceCircle(10), "write correct code for circumference of circle");
    }

    @Test
    @Disabled
    @DisplayName("Testing add method")
    void failMethod(){
        fail("the add method should add two number");
    }

//    @Test
//    void add(){
//        MathUtils mu = new MathUtils();
//        int expected = 2;
//        int actual = mu.add(1, 1);
////         assertEquals(expected, actual);
//        assertEquals(expected, actual, "the add method should add two number");
//
//    }
//
//    @Test
//    void divideTest() {
//        MathUtils mu = new MathUtils();
////        assertThrows(ArithmeticException.class, () -> mu.divide(1, 0), "can't divide by zero");
//        assertThrows(NullPointerException.class, () -> mu.divide(1, 0), "can't divide by zero");
//    }
//
//    @Test
//    void circumferenceCircle(){
//        MathUtils mu = new MathUtils();
//        mu.circumferenceCircle(10);
//        assertEquals(314.1592653589793,  mu.circumferenceCircle(10), "write correct code for circumference of circle");
//    }
}