package ru.job4j.maps;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Собственная реализация HashMap
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 19.07.2018
 * @param <K> - тип ключа
 * @param <V> - тип хранимого значения
 */
public class SimpleHashMap<K, V> {

    private Entry<K, V>[] tab;
    private int count;

    public class Entry<K, V> {
        private K key;
        private V value;

        public K getKey() {
            return key;
        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" + "key=" + key + ", value=" + value + '}';
        }
    }

    public SimpleHashMap() {
        tab = new Entry[10];
        count = 0;
    }

    private void resize() {
        int fixCount = count;
        Entry<K, V> [] newTab = new Entry[tab.length * 2];
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                tabInsert(tab[i].key, tab[i].value, newTab);
            }
        }
        count = fixCount;
        tab = newTab;
    }

    private int getPosition(K key, Entry<K, V>[] table) {
        int keyHash = key.hashCode();
        int hash = keyHash ^ (keyHash >>> 16);
        return  (hash % table.length);
    }


    private boolean tabInsert(K key, V value, Entry<K, V>[] table) {
        boolean result = true;
        int position = getPosition(key, table);
        if (table[position] == null) {
            table[position] = new Entry<>(key, value);
        } else if (table[position].key.equals(key)) {
            table[position].value = value;
        } else {
            result = false;
        }
        if (result) {
            count++;
        }
        return result;
    }

    /**
     * Добавляет пару ключ-значение в соответствие
     * @param key - ключ
     * @param value - значение
     * @return - истина, если успешно
     */
    public boolean insert(K key, V value) {
        if (count * 2 >= tab.length - 1) {
            resize();
        }
        return tabInsert(key, value, tab);
    }

    /**
     * Получает значение по известному ключу
     * @param key - ключ
     * @return - полученное значение, если не удалось найти - будет выборошено исключение NoSuchElementException
     */
    public V get(K key) {
        int position = getPosition(key, tab);
        if (tab[position] == null || !tab[position].key.equals(key)) {
            throw new NoSuchElementException();
        }
        return tab[position].value;
    }

    /**
     * Удаление значения по известному ключу
     * @param key - ключ
     * @return - истина, если успешно
     */
    public boolean delete(K key) {
        boolean result = false;
        int position = getPosition(key, tab);
        if (tab[position] != null) {
            tab[position] = null;
            count--;
            result = true;
        }
        return result;
    }

    // Возвращает набор пар соответствия, для перебора циклом foreach
    public EntrySet<K, V> getEntrySet() {
        return new EntrySet<>();
    }

    public class EntrySet<K, V> implements Iterable<Entry<K, V>> {
        Entry<K, V> [] storage;

        public EntrySet() {
            storage = new Entry[count];
            int index = 0;
            for (int i = 0; i < tab.length; i++) {
                if (tab[i] != null) {
                    storage[index++] = (Entry<K, V>) tab[i];
                }
            }
        }

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new Iterator<Entry<K, V>>() {
                int index = 0;
                @Override
                public boolean hasNext() {
                    return (index < storage.length);
                }

                @Override
                public Entry<K, V> next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return storage[index++];
                }
            };
        }
    }

}


