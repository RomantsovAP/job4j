package ru.job4j.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор итераторов, класс для конвертации итератора итераторов целых чисел в просто итератор целых чисел
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 07.07.2018
 */
public class IteratorOfIterators {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> currentIterator;
            {
                if (it.hasNext()) {
                    currentIterator = it.next();
                } else {
                    currentIterator = new ArrayList<Integer>().iterator();
                }
            }

            @Override
            public boolean hasNext() {
                boolean result = true;
                while (!currentIterator.hasNext()) {
                    if (it.hasNext()) {
                        currentIterator = it.next();
                    } else {
                        result = false;
                        break;
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return currentIterator.next();
                }
            }
        };
    }

}
