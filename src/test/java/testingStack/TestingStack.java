package testingStack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestingStack {
    Stack<Object> st;

    @Test
    void isInstantiatedWithNew() {
        new Stack<>();
    }

    @Nested
    class WhenNew {
        @BeforeEach
        void createNewStack() {
            st = new Stack<>();
        }

        @Test
        void isEmpty() {
            assertTrue(st.isEmpty());
        }

        @Test
        void throwsExceptionWhenPopped() {
            assertThrows(EmptyStackException.class, () -> st.pop());
        }

        @Test
        void throwsExceptionWhenPeeked() {
            assertThrows(EmptyStackException.class, () -> st.peek());
        }

        @Nested
        class AfterPushing {
            String str = "abc";

            @BeforeEach
            void pushAnElement() {
                st.push(str);
            }

            @Test
            void isNotEmpty() {
                assertFalse(st.isEmpty());
            }

            @Test
            void returnElementWhenPopped() {
                assertEquals(str, st.pop());
                assertTrue(st.isEmpty());
            }

            @Test
            void returnElementWhenPeeked() {
                assertEquals(str, st.peek());
                assertFalse(st.isEmpty());
            }
        }
    }
}
