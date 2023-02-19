package org.example.data_structures;

public final class GenericQueue<T> {
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

    public GenericQueue() {}

    /**
     * Anexa um elemento na fila. Procedimento de complexidade
     * linear O(1).
     * @param entity - elemento a ser anexado;
     */
    public void enqueue(T entity) {
        Storage<T> newStorage = new Storage<>(entity);
        if (size == 0) {
            this.head = newStorage;
            this.tail = newStorage;
        } else {
            this.tail.next = newStorage;
            this.tail = newStorage;
        }
        size++;
    }

    /**
     * Remove o primeiro elemento na lista. Função de complexidade
     * linear O(1).
     * @return T.
     */
    public T dequeue() {
        Storage<T> tempStorage = this.head;
        T tempValue = this.head.value;
        this.head = tempStorage.next;
        size--;
        return tempValue;
    }

    /**
     * Retorna sem remover o último elemento na fila. Procedimento
     * de complexidade linear O(1).
     * @return T.
     */
    public T rear() {
        return this.tail.value;
    }

    /**
     * Retorna sem remover o primeiro elemento na fila. Procedimento
     * de complexidade linear O(1).
     * @return T.
     */
    public T front() {
        return this.head.value;
    }

    /**
     * Retorna true caso a fila esteja vazia.
     * @return boolean.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Retorna o tamanho da fila.
     * @return int.
     */
    public int size() {
        return size;
    }
}
