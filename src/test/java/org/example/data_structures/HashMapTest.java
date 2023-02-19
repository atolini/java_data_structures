package org.example.data_structures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    @Test
    void putAndGetTest() {
        Random r = new Random();
        Integer randomNumber1 = r.nextInt(1000);
        Integer randomNumber2 = r.nextInt(1000);
        Integer randomNumber3 = r.nextInt(1000);

        HashMap<Integer, String> myHash = new HashMap<>(5);
        myHash.put(randomNumber1, "Lucas");
        myHash.put(randomNumber2, "Gabriela");
        myHash.put(randomNumber3, "Rafael");

        assertEquals(myHash.get(randomNumber1), "Lucas");
        assertEquals(myHash.get(randomNumber2), "Gabriela");
        assertEquals(myHash.get(randomNumber3), "Rafael");

        for (int i = 1; i <= 1000; i++) {
            // Testando a função hash;
            assertNull(myHash.get(r.nextInt(1001, 2000)));
        }
    }

    @Test
    void hashTest() {
        // A cada 10.000 itens espera-se uma colisão inferior a 2;
        final HashMap<Integer, String> myHash = new HashMap<>();
        Long count = 0L;
        AtomicInteger atomic = new AtomicInteger(0);

        do {
            Stream.generate(atomic::incrementAndGet)
                    .limit(10).forEach(i -> myHash.put(i, "a"));
            count += 10;
        } while (count <= 10000);

        assertTrue(myHash.getColisions() <= 2);
    }

    @Test
    void resize() {
        final HashMap<Integer, String> myHash = new HashMap<>(5);
        List<String> itens = new ArrayList<>();

        itens.add("Primeiro");
        itens.add("Segundo");
        itens.add("Terceiro");
        itens.add("Quarto");
        itens.add("Quinto");

        AtomicInteger atomic = new AtomicInteger(0);

        for (int i = 1; i <= 1000; i++) {
            for (String s : itens) {
                myHash.put(atomic.incrementAndGet(), s);
            }
        }

        for (int i = 1; i <= 5000; i++) {
            String s = myHash.get(i);
            assertTrue(itens.contains(s));
        }
    }

    @Test
    void clear() {
        Random r = new Random();
        Integer randomNumber = r.nextInt(1000);

        HashMap<Integer, String> myHash = new HashMap<>(5);

        myHash.put(randomNumber, "Lucas");

        // Deve retornar o item
        assertEquals(myHash.get(randomNumber), "Lucas");

        // Após a limpeza deve retornar null;
        myHash.clear(5);
        assertNull(myHash.get(randomNumber));
    }

    @Test
    void delete() {
        Random r = new Random();
        Integer randomNumber = r.nextInt(1000);

        HashMap<Integer, String> myHash = new HashMap<>(5);

        myHash.put(randomNumber, "Lucas");

        // Deve retornar o item
        assertEquals(myHash.get(randomNumber), "Lucas");

        // Após a limpeza deve retornar null;
        myHash.delete(randomNumber);
        assertNull(myHash.get(randomNumber));
    }
}