package org.example.data_structures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    @Test
    void pushPopTop() {
        AtomicInteger atomic = new AtomicInteger(0);

        Stack s = new Stack(100);

        // Criando Stream de 100 números
        // Cada número é inserido e retirado
        Stream.generate(atomic::incrementAndGet)
                .limit(100)
                .toList()
                .forEach(i -> {
                    s.push(i);
                    assertEquals(s.top(), i);
                    assertEquals(s.pop(), i);
                 });
    }

    @Test
    void resize() {
        AtomicInteger atomic = new AtomicInteger(0);

        Stack s = new Stack(5);

        // Criando Stream de 100 números
        // Cada número é inserido e retirado
        List<Integer> lista = new ArrayList<>(Stream.generate(atomic::incrementAndGet)
                .limit(100)
                .toList());

        List<Integer> clone = new ArrayList<>(100);

        lista.forEach(i -> {
            s.push(i);
            assertEquals(s.size(), i);
            clone.add(i);
        });

        lista.forEach(i -> {
            clone.remove(i);
            s.pop();
        });

        assertTrue(clone.isEmpty());
        assertTrue(s.isEmpty());
    }

    @Test
    void isEmpty() {
        Stack s = new Stack(10);
        assertTrue(s.isEmpty());
    }

    @Test
    void size() {
        AtomicInteger atomic = new AtomicInteger(0);

        // Criando uma Stack
        Stack s = new Stack();

        // Criando uma lista com 100 números
        List<Integer> lista = new ArrayList<>(Stream.generate(atomic::incrementAndGet)
                .limit(100)
                .toList());

        // Percorrendo a lista e adicionando os números na Stack;
        for (Integer i : lista) {
            s.push(i);
        }

        // Verificando o resultado
        assertEquals(100, s.size());
    }
}