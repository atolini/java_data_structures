package org.example.data_structures;

public class Queue {
    private int[] storage;
    /* Indica aonde deve ocorrer a inserção */
    private int pointer = 0;

    /* Indica a capacidade do array que dá suporte a fila */
    private int capacity = 0;

    /* Indica o próximo a ser removido na fila */
    private int startQueue = 0;

    /* Quantos itens estão na fila */
    private int load = 0;

    public Queue() {
        storage = new int[10];
        this.capacity = 9;
    }

    public Queue(int initialCapacity) {
        if (initialCapacity > 0) {
            this.storage = new int[initialCapacity];
            this.capacity = --initialCapacity;
        } else {
            throw new RuntimeException("A capacidade inicial não pode ser negativa!");
        }
    }

    /**
     * Anexa um elemento na fila. Procedimento de complexidade
     * linear O(1).
     * @param value - int a ser anexado;
     */
    public void enqueue(int value) {
        if (this.pointer >= this.capacity) {
            int newCapacity = capacity * 2;
            int[] newStorage = new int[newCapacity];
            int newPointer = 0;
            int tempIdx = this.startQueue;

            do {
               newStorage[newPointer] = this.storage[tempIdx];
               tempIdx++;
               newPointer++;
            } while (tempIdx < capacity);

            this.storage = newStorage;
            this.pointer = newPointer;
            this.capacity = newCapacity;
            this.startQueue = 0;
        }

        this.storage[pointer] = value;
        load++;
        pointer++;
    }

    /**
     * Remove o primeiro inteiro na lista. Função de complexidade
     * linear O(1).
     * @return int.
     */
    public int dequeue() {
        int temp = this.storage[this.startQueue];
        this.storage[this.startQueue] = 0;
        this.startQueue++;
        this.load--;
        return temp;
    }

    /**
     * Retorna sem remover o primeiro inteiro na fila. Procedimento
     * de complexidade linear O(1).
     * @return int.
     */
    public int front() {
        return this.storage[this.startQueue];
    }

    /**
     * Retorna sem remover o último inteiro na fila. Procedimento
     * de complexidade linear O(1).
     * @return int.
     */
    public int rear() {
        return this.storage[--this.pointer];
    }

    /**
     * Retorna o tamanho da fila.
     * @return int.
     */
    public int size() {
        return this.pointer - this.startQueue;
    }

    /**
     * Retorna true se a fila estiver vazia.
     * @return boolean.
     */
    public boolean isEmpty() {
        return this.load == 0;
    }
}
