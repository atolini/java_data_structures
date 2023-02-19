package org.example.data_structures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericStackTest {
    @Test
    void pushPopSizeTop() {
        GenericStack<String> genericStack = new GenericStack<>();
        List<String> strings = new ArrayList<>();
        strings.add("Primeiro");
        strings.add("Segundo");
        strings.add("Terceiro");

        int count = 1;
        for (String s : strings) {
            genericStack.push(s);
            assertEquals(genericStack.size(), count);
            count++;
        }

        count = 3;
        assertEquals(genericStack.top(), "Terceiro");
        assertEquals(genericStack.pop(), "Terceiro");
        count--;
        assertEquals(genericStack.size(), count);

        assertEquals(genericStack.top(), "Segundo");
        assertEquals(genericStack.pop(), "Segundo");
        count--;
        assertEquals(genericStack.size(), count);

        assertEquals(genericStack.top(), "Primeiro");
        assertEquals(genericStack.pop(), "Primeiro");
        count--;
        assertEquals(genericStack.size(), count);
    }

    @Test
    void isEmpty() {
        GenericStack<String> genericStack = new GenericStack<>();
        assertTrue(genericStack.isEmpty());
        assertNull(genericStack.pop());
        assertNull(genericStack.top());

        genericStack.push("Teste");
        assertFalse(genericStack.isEmpty());
        assertNotNull(genericStack.top());
        assertNotNull(genericStack.pop());
    }
}