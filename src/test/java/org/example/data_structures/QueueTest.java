package org.example.data_structures;

import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    @Test
    void enqueue() {
        final Queue q = new Queue();
        Stream.generate(() -> new Random().nextInt(100))
                .limit(110).forEach(q::enqueue);

        for (int i = 1; i <= 110; i++) {
            q.dequeue();
        }

        assertTrue(q.isEmpty());
    }

    @Test
    void dequeue() {
        final Queue q = new Queue(3);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        assertEquals(q.dequeue(), 1);
        assertEquals(q.dequeue(), 2);
        assertEquals(q.dequeue(), 3);
    }

    @Test
    void front() {
        final Queue q = new Queue(3);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        assertEquals(q.front(), 1);
    }

    @Test
    void rear() {
        final Queue q = new Queue(3);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        assertEquals(q.rear(), 3);
    }

    @Test
    void size() {
        int[] lista = {100, 99, 55, 25};
        for (int i : lista) {
            final Queue q = new Queue();
            Stream.generate(() -> new Random().nextInt(100))
                    .limit(i).forEach(q::enqueue);
            assertEquals(q.size(), i);
        }
    }

    @Test
    void isEmpty() {
        Queue q = new Queue();
        assertTrue(q.isEmpty());
        q.enqueue(1);
        assertFalse(q.isEmpty());
    }
}