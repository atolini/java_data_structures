package org.example.data_structures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericQueueTest {

    @Test
    void enqueueDequeueSizeRearFrontTest() {
        GenericQueue<String> q = new GenericQueue<>();
        List<String> strings = new ArrayList<>();
        strings.add("Primeiro");
        strings.add("Segundo");
        strings.add("Terceiro");

        int count = 1;
        for (String s : strings) {
            q.enqueue(s);
            assertEquals(q.size(), count);
            count++;
        }

        // Testando Read
        assertEquals(q.rear(), "Terceiro");

        // Testando o front
        assertEquals(q.front(), "Primeiro");

        count = 3;
        assertEquals(q.dequeue(), "Primeiro");
        count--;
        assertEquals(q.size(), count);

        assertEquals(q.dequeue(), "Segundo");
        count--;
        assertEquals(q.size(), count);

        assertEquals(q.dequeue(), "Terceiro");
        count--;
        assertEquals(q.size(), count);
    }

    @Test
    void isEmptyTest() {
        GenericQueue<String> q = new GenericQueue<>();
        assertTrue(q.isEmpty());

        q.enqueue("Primeiro");
        assertFalse(q.isEmpty());
    }
}