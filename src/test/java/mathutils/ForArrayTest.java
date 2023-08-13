package mathutils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForArrayTest {
    @Test
    void findMax() {
        ForArray m = new ForArray();
        assertEquals(4, m.findMax(new int[]{1, 2, 3, 4}));
        assertEquals(-1, m.findMax(new int[]{-12,-1,-3,-4,-2}));
    }

    @Test
    void reverseString(){
        ForArray m = new ForArray();
        assertEquals("olleH dlroW ", m.reverseString("Hello World"));
    }
}