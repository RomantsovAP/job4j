package ru.job4j.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор четных чисел для массива целыых чисел
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 07.07.2018
 *
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] array;
    private int currentPosition;

    public EvenNumbersIterator(int[] array) {
        this.array = new int[array.length];
        int length = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                length++;
                this.array[length - 1] = array[i];
            }
        }
        this.array = Arrays.copyOf(this.array, length);
        currentPosition = -1;
    }

    @Override
    public boolean hasNext() {
        return array.length > currentPosition + 1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[++currentPosition];
    }
}
