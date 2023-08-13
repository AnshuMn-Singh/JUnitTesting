package mathutils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class MathUtilsTest1 {
    MathUtils mu = new MathUtils();

    @Test
    void assertEqualTest(){
        assertEquals(3, mu.add(1, 2));
        assertEquals(2, mu.add(1, 1), () -> "hello world");
    }

    @Test
    void assertArrayTest(){
        assertArrayEquals(new int[]{1,2,3,4}, new int[]{1,2,3,4});
    }

    @Test
    void assertIterableTest(){
        Iterable<Integer> l1 = new ArrayList<>(Arrays.asList(1,2,3,4));
        Iterable<Integer> l2 = new ArrayList<>(Arrays.asList(1,2,3,4));

        assertIterableEquals(l1, l2);
    }

    @Test
    void assertNotNullTest(){
        String s = "A";
        assertNotNull(s);
    }

    @Test
    void assertTrueTest(){
        boolean t = true;
        assertTrue(t);
    }

    @Test
    void assertFalseTest(){
        boolean t = false;
        assertFalse(t);
    }

    @Test
    void assertThrowTest(){
        assertThrows(ArithmeticException.class,  () -> mu.divide(1,0));
    }

    @Test
    void assertAllTest(){
        assertAll(
                () -> assertEquals(4, mu.add(2,2)),
                () -> assertEquals(4, mu.add(2,2)),
                () -> assertEquals(4, mu.add(2,2)),
                () -> assertEquals(4, mu.add(2,2))
        );
    }

    @Test
    void assertFails(){
        fail("h");
    }
}
