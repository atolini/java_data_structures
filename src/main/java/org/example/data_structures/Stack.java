package org.example.data_structures;

public final class Stack {
    private int[] storage;
    private int size = 0;
    private int capacity = 0;

    public Stack() {
        this.storage = new int[10];
        this.capacity = 10;
    }

    public Stack(int initialCapacity) {
        if (initialCapacity > 0) {
            this.capacity = initialCapacity;
            this.storage = new int[initialCapacity];
        } else {
            throw new RuntimeException("A capacidade inicial não pode ser negativa!");
        }
    }

    /**
     * Anexa um elemento na pilha. Procedimento de complexidade
     * linear O(1).
     * @param value - inteiro a ser anexado;
     */
    public void push(int value) {
        if (this.size < this.capacity) {
            this.storage[this.size] = value;
            this.size++;
        } else {
            int tempNewCapacity = this.capacity * 2;
            int[] tempNewStorage = new int[tempNewCapacity];
            int tempNewSize = 0;
            for (int a : storage) {
                tempNewStorage[tempNewSize] = this.storage[tempNewSize];
                tempNewSize++;
            }
            tempNewStorage[tempNewSize] = value;
            tempNewSize++;
            this.storage = tempNewStorage;
            this.size = tempNewSize;
            this.capacity = tempNewCapacity;
        }
    }

    /**
     * Remove o primeiro elemento na pilha. Função de complexidade
     * linear O(1).
     * @return int.
     */
    public int pop() {
        int tempIndex = this.size;
        tempIndex--;
        int temp = storage[tempIndex];
        storage[tempIndex] = 0;
        size--;
        return temp;
    }

    /**
     * Retorna sem remover o primeiro int no topo da pilha.
     * @return int.
     */
    public int top() {
        int tempIndex = this.size;
        tempIndex--;
        return storage[tempIndex];
    }

    /**
     * Retorna o tamanho da pilha.
     * @return int.
     */
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Retorna true caso a pilha esteja vazia.
     * @return boolean.
     */
    public int size() {
        return this.size;
    }
}
