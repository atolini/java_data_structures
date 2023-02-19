package org.example.data_structures;

public class SingleList<T> {
    /* Primeiro Storage na lista */
    private Storage<T> head;

    /* Último Storage na lista */
    private Storage<T> tail;

    /* Tamanho da lista */
    private int size = 0;

    /* Bucket interno que armazena os valores e encadeia a lista */
    private final class Storage<T> {
        private T value;
        private Storage<T> next;
        public Storage(T value) {
            this.value = value;
        }
    }

    public SingleList() {}

    /**
     * Anexa um elemento na lista. Procedimento de complexidade
     * linear O(1).
     * @param entity - elemente a ser anexado;
     */
    public void push(T entity) {
        Storage<T> tempStorage = new Storage<>(entity);
        if(this.size == 0) {
            this.head = tempStorage;
            this.tail = tempStorage;
        } else {
            this.tail.next = tempStorage;
            this.tail = tempStorage;
        }
        size++;
    }

    /**
     * Remove o último item da lista. Complexidade O(n)
     * sendo n o número de itens na lista.
     * @return T.
     */
    public T pop() {
        if (this.size > 1) {
            T returnValue = this.tail.value;
            // Definindo uma nova cauda
            Storage<T> iterator = this.head;
            int target = this.size;
            target--;
            for (int i = 1; i < target; i++) {
                iterator = iterator.next;
            }
            iterator.next = null;
            this.tail = iterator;
            this.size--;
            return returnValue;
        }

        if (this.size == 1) {
            T returnValue = this.tail.value;
                this.size = 0;
                this.tail = null;
                this.head = null;
            return returnValue;
        }

        return null;
    }

    /**
     * Anexa um elemento na lista no índice indicado. Complexidade
     * O(n) sendo n o número de elementos na lista.
     * @param index - indíce desejado.
     * @param entity
     */
    public void insert(int index, T entity) {
        Storage<T> tempStorage = new Storage<>(entity);

        // Caso a lista esteja vazia;
        if (this.size == 0) {
            // Caso a Lista esteja vazia e a solicitação seja no idx 1;
            if (index == 1) {
                this.head = tempStorage;
                this.tail = tempStorage;
                return;
            }

            // Caso a Lista esteja vazia e a solicitação seja no idx > 1;
            if (index > 1) {
                this.head = new Storage<>(null);
                Storage<T> iterator = this.head;
                this.size++;
                int target = index;
                target--;
                do {
                    // Cria storages sem valores
                    iterator.next = new Storage<>(null);
                    iterator = iterator.next;
                    this.size++;
                } while (this.size < target);
                iterator.next = tempStorage;
                this.size++;
                this.tail = iterator.next;
                return;
            }
        } else {
          // Caso a lista contenha algum item;

            // Caso o user deseja na primeira posição
            if (index <= 1) {
                this.head.value = tempStorage.value;
            }

            // Caso o user deseja na última posição
            if (index == this.size) {
                this.tail.value = tempStorage.value;
            }

            // Caso o user deseja numa posição superior ao tamanho atual
            if (index > this.size) {
                int target = index;
                target--;
                int pointer = 1;
                Storage<T> iterator = this.head;
                do {
                    // Cria storages sem valores
                    if (iterator.next == null) {
                        iterator.next = new Storage<>(null);
                        this.size++;
                    }
                    iterator = iterator.next;
                    pointer++;
                } while (pointer < target);
                iterator.next = tempStorage;
                this.size++;
                this.tail = tempStorage;
            }

            // Caso o user deseja override algum valor
            if (index < this.size) {
                int pointer = 1;
                Storage<T> iterator = this.head;
                do {
                    iterator = iterator.next;
                    pointer++;
                } while (pointer < index);
                iterator.value = tempStorage.value;
            }
        }
    }

    /**
     * Remove o elemento no índice indicado. Complexidade O(n)
     * sendo n o número de elementos na lista.
     * @param index
     */
    public void remove(int index) {
        if (index > 1 && index < this.size) {
            int pointer = 1;
            Storage<T> iterator = this.head;
            Storage<T> beforeStorage = null;
            Storage<T> afterStorage = null;
            int beforeIndex = --index;
            int afterIndex = index + 2;
            do {
                if (pointer == beforeIndex) {
                    beforeStorage = iterator;
                }
                if (pointer == afterIndex) {
                    afterStorage = iterator;
                }
                iterator = iterator.next;
                pointer++;
            } while (pointer <= afterIndex);
            beforeStorage.next = afterStorage;
            this.size--;
            return;
        }

        if (index == 1) {
            Storage<T> temp = this.head.next;
            this.head = temp;
            this.size--;
            return;
        }

        if (index == size) {
            int pointer = 1;
            int target = this.size;
            target--;
            Storage<T> iterator = this.head;
            while (pointer < target) {
                iterator = iterator.next;
                pointer++;
            }
            iterator.next = null;
            this.size--;
            return;
        }

        throw new UnsupportedOperationException("Fora dos limites da lista!");
    }

    /**
     * Retorna o tamanho da lista.
     * @return int
     */
    public int size() {
        return this.size;
    }

    public T elementAt(int index) {
        if (index <= this.size && index >= 1) {
            Storage<T> iterator = this.head;
            for (int i = 1; i < index; i++) {
                iterator = iterator.next;
            }
            return iterator.value;
        }
        return null;
    }

    /**
     * Retorna uma string representando a lista.
     * Complexidade O(n) sendo n o número de itens na lista.
     * @return String
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();
        Storage<T> iterator = this.head;
        for (int i = 0; i < this.size; i++) {
            sb.append(iterator.value.toString());
            if (iterator.next != null) {
                sb.append(" - ");
            }
            iterator = iterator.next;
        }
        return sb.toString();
    }
}
