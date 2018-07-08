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
    private int checkedPosition;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
        currentPosition = -1;
        checkedPosition = -1;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = currentPosition + 1; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result = true;
                checkedPosition = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentPosition = checkedPosition;
        return array[currentPosition];
    }
}
