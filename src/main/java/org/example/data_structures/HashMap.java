package org.example.data_structures;

import java.math.BigDecimal;
import java.math.MathContext;

public final class HashMap<K, E> {
    /* Array de armazenamento dos buckets */
    private Storage[] storages;

    /* Capacidade de armazenamento no this.storages */
    private int currentCapacity = 0;

    /*
     * Armazena a quantidade de colisões, isto é, quantos itens estão
     * armazenados em Linked List. Quanto maior o número, mais lento é
     * a busca;
     */
    private int colisions = 0;

    /* Quantos itens estão armazenados em indíces (this.storages) */
    private int size = 0;

    /* Atual o valor de preenchimento */
    private BigDecimal currentLoadFactor = new BigDecimal("0.0");

    /* Fator de preenchimento definido pelo User */
    private BigDecimal targetLoadFactor;

    /*
     * Quantos itens entrou na lista, independente onde foi armazenado numa
     * Liked List ou num índice. Quando o valor ultrapassa a capacidade a
     * lista é redimensionada;
     */
    private int entry = 0;

    /* Bucket interno que armazena os valores */
    private final class Storage<E> {
        private E value;
        private K key;
        private Storage<E> next;
        public Storage(K key, E value) {
            this.key = key;
            this.value = value;
        }
    }

    /* Construtores */
    public HashMap(int initialCapacity, double loadFactor) {
        this.storages = new Storage[initialCapacity];
        this.currentCapacity = initialCapacity;
        this.targetLoadFactor = new BigDecimal(loadFactor).round(new MathContext(3));
        updateLoadFactor();
    }

    public HashMap(int initialCapacity) {
        this.storages = new Storage[initialCapacity];
        this.currentCapacity = initialCapacity;
        this.targetLoadFactor = new BigDecimal("0.7");
        updateLoadFactor();
    }

    public HashMap() {
        this.storages = new Storage[100];
        this.currentCapacity = 100;
        this.targetLoadFactor = new BigDecimal("0.7");
        updateLoadFactor();
    }

    /**
     * Metodo para anexar um item. Em casos de colisões o item é
     * armazenado numa Linked List;
     * Complexidade O(1). Em piores casos, o metodo tem complexidade
     * relativa de O(n) sendo o n o número de itens na lista (this.entry);
     * @param key - Chave.
     * @param entity - Objeto associado a chave.
     */
    public void put(K key, E entity) {
        Storage<E> newEntityStorage = new Storage<>(key, entity);
        this.entry++;

        int k = this.generateKey(key);
        if (k > currentCapacity) {
        throw new UnsupportedOperationException("O valor de retorno da função hashcode() é muito grande!");
        } else {
            if (this.storages[k] == null) {
                this.storages[k] = newEntityStorage;
                this.size++;
            } else {
                Storage<E> actualStorage = this.storages[k];
                boolean duplicated = false;

                while (true) {
                    if (actualStorage.key.equals(newEntityStorage.key)) {
                        duplicated = true;
                        break;
                    }

                    if (actualStorage.next != null) {
                        actualStorage = actualStorage.next;
                    } else {
                        break;
                    }
                }

                if (!duplicated) {
                    actualStorage.next = newEntityStorage;
                    this.colisions++;
                } else {
                    actualStorage.value = newEntityStorage.value;
                }
            }
        }
        updateLoadFactor();
    }

    /**
     * Metodo para anexar um item. Em casos de colisões o item é
     * armazenado numa Linked List;
     * Complexidade O(1). Em piores casos, o metodo tem complexidade
     * relativa de O(n) sendo o n o número de itens na lista (this.entry);
     * @param key - Chave
     * @return E.
     */
    public E get(K key) {
        int k = this.generateKey(key);
        Storage<E> entity = this.storages[k];

        if (entity == null) {
            return null;
        }

        if (entity.next == null && entity.key.equals(key)) {
            return entity.value;
        } else {
            Storage<E> iterator = this.storages[k];
            do {
                if (iterator.key.equals(key)) {
                    return iterator.value;
                }

                iterator = iterator.next;
            } while (iterator != null);
        }

        return null;
    }

    /**
     * Procedimento para limpar a lista;
     * @param newCapacity
     */
    public void clear(int newCapacity) {
        this.storages = new Storage[newCapacity];
        this.size = 0;
        this.entry = 0;
        this.colisions = 0;
    }

    /**
     * Procedimento para limpar a lista.
     */
    public void clear() {
        this.storages = new Storage[10];
        this.size = 0;
        this.entry = 0;
        this.colisions = 0;
    }

    /**
     * Metodo de exclusão;
     * Complexidade O(1). Em piores casos O(n) sendo n o número de itens na lista;
     * @param key - chave.
     */
    public void delete(K key) {
        this.entry--;
        int k = generateKey(key);
        if (this.storages[k].next == null) {
            if (this.storages[k].key.equals(key)) {
                this.storages[k] = null;
                this.size--;
            }
        } else {
            Storage<E> beforeStorage = null;
            Storage<E> afterStorage = null;
            Storage<E> iterator = this.storages[k];

            while (true) {
                if (iterator.key.equals(key)) {
                    if (iterator.next != null) {
                        afterStorage = iterator.next;
                    }
                    break;
                } else {
                    beforeStorage = iterator;
                    if (iterator.next != null) {
                        iterator = iterator.next;
                    } else {
                        iterator = null;
                        beforeStorage = null;
                        break;
                    }
                }
            }

            if (beforeStorage != null && afterStorage != null) {
                beforeStorage.next = afterStorage;
                this.storages[k] = beforeStorage;
            }

            if (afterStorage != null && beforeStorage == null) {
                this.storages[k] = afterStorage;
            }
        }
    }

    /* Metodos de apoio */

    /*
     * Gerador de chave (K key);
     * Utiliza a função nativa hashCode() que retorna um Integer;
     * O cálculo de chave é proporcional ao tamanho da lista (this.currentCapacity);
     * Quanto menor a capacidade maior a possibilidade de colisão;
     * Complexidade O(1);
     */
    private int generateKey(K key) {
        return key.hashCode() % this.currentCapacity;
    }

    /*
     * Metodo de Redimensionamento;
     * Torna o array 2x maior;
     * Complexidade O(n) sendo n o número de itens na lista;
     */
    private void resize() {
        int newCapacity = this.currentCapacity * 2;
        Storage<E>[] copy = this.storages;
        this.currentCapacity = newCapacity;
        this.storages = new Storage[newCapacity];

        // Zerando propriedades
        this.colisions = 0;
        this.size = 0;
        this.entry = 0;

        for (Storage<E> s : copy) {
            if (s != null) {

                // Realocando conforme nova Capacity;
                if (s.next == null) {
                    this.put(s.key, s.value);
                } else {
                    Storage<E> iterator = s;
                    do {
                        this.put(iterator.key, iterator.value);
                        iterator = iterator.next;
                    } while (iterator != null);
                }

            }
        }
    }

    /*
     * Metodo O(1);
     * Trata da saúde do Map, verificando o loadFactor atual com o máximo;
     * Verifica se a lista está próxima de exceder limite;
     * Aciona o redimensionamento;
     */
    private void updateLoadFactor() {
        if (this.size > 0) {
            this.currentLoadFactor = BigDecimal.valueOf(this.size).divide(BigDecimal.valueOf(this.currentCapacity));
        }

        if ((this.currentLoadFactor.doubleValue() > this.targetLoadFactor.doubleValue()) ||
                this.entry > this.currentCapacity) {
            resize();
        }
    }

    public int getColisions() {
        return this.colisions;
    }
}
