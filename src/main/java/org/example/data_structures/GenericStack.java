package org.example.data_structures;

public final class GenericStack<T> {
    /* Primeiro Storage na lista */
    private Storage<T> head;

    /* Tamanho da lista */
    private int size;

    /* Bucket interno que armazena os valores e encadeia a lista */
    private final class Storage<T> {
        private T value;
        private Storage<T> next;
        public Storage(T value) {
            this.value = value;
        }
    }

    public GenericStack() {
        this.size = 0;
    }

    /**
     * Anexa um elemento na pilha. Procedimento de complexidade
     * linear O(1).
     * @param entity - elemente a ser anexado;
     * @return boolean - indica o sucesso da inserção;
     */
    public boolean push(T entity) {
        Storage<T> s = new Storage<T>(entity);
        if (this.size == 0) {
            head = s;
        } else {
            s.next = this.head;
            this.head = s;
        }
        size++;
        return true;
    }

    /**
     * Remove o primeiro elemento na pilha. Função de complexidade
     * linear O(1).
     * @return T.
     */
    public T pop() {
        if (this.size == 0) {
            return null;
        }

        T returnValue = this.head.value;
        Storage<T> temp = this.head;
        this.head = temp.next;
        temp = null;
        size--;
        return returnValue;
    }

    /**
     * Retorna sem remover o primeiro elemento no topo da pilha.
     * @return T.
     */
    public T top() {
        if (this.size == 0) {
            return null;
        }
        return this.head.value;
    }

    /**
     * Retorna o tamanho da pilha.
     * @return int.
     */
    public int size() {
        return this.size;
    }

    /**
     * Retorna true caso a pilha esteja vazia.
     * @return boolean.
     */
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }
}
