package org.example.data_structures;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SingleListTest {

    @Test
    void pushAndPopTest() {
        SingleList<String> s = new SingleList<>();
        s.push("Lucas");
        assertEquals(s.pop(), "Lucas");
    }

    @Test
    void insertTest() {
        // Inserindo numa lista vazia
        SingleList<String> listaVazia = new SingleList<>();
        listaVazia.insert(100, "100");
        assertEquals(listaVazia.elementAt(100), "100");

        // Do item 1 ao 99 o resultado deve ser null;
        for (int i = 1; i <= 99; i++) {
            assertNull(listaVazia.elementAt(i));
        }

        // Alterar os valores de uma lista preenchida
        final SingleList<Integer> listaPreenchida = new SingleList<>();
        Stream.generate(() -> new Random().nextInt(100))
                .limit(100).forEach(i -> listaPreenchida.push(i));

        // Inserções
        listaPreenchida.insert(4, 101);
        listaPreenchida.insert(55, 102);
        listaPreenchida.insert(68, 103);
        listaPreenchida.insert(100, 100);
        listaPreenchida.insert(1, 965);


        // Asserts
        assertEquals(listaPreenchida.elementAt(4), 101);
        assertEquals(listaPreenchida.elementAt(55), 102);
        assertEquals(listaPreenchida.elementAt(68), 103);

        // Testando inserções feitas no primeiro e último item;
        assertEquals(listaPreenchida.elementAt(1), 965);
        assertEquals(listaPreenchida.elementAt(100), 100);
    }

    @Test
    void remove() {
        final SingleList<Integer> listaPreenchida = new SingleList<>();
        Stream.generate(() -> new Random().nextInt(100))
                .limit(100).forEach(i -> listaPreenchida.push(i));

        listaPreenchida.remove(4);
        Integer a = listaPreenchida.elementAt(4);
        System.out.println(a);
    }
}