package ru.job4j.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {

    private int[][] array;
    private int currentLine;
    private int currentPosition;

    public MatrixIterator(int[][] array) {
        this.array = array;
        currentPosition = -1;
        currentLine = -1;
    }

    private boolean hasNextInLine(int line, int pos) {
        return line != -1 && (array[line].length > pos + 1);
    }

    private boolean hasNextLine(int line) {
        return (array.length > line + 1);
    }

    private boolean seekForNextItem(int line, int pos) {
        boolean result;
        if (!hasNextInLine(line, pos) && !hasNextLine(line)) {
            result = false;
        } else if (hasNextInLine(line, pos)) {
            result = true;
        } else {
            result = seekForNextItem(line + 1, -1);
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return seekForNextItem(currentLine, currentPosition);
    }

    @Override
    public Integer next() {
        Integer result;
        if (!hasNextInLine(currentLine, currentPosition) && !hasNextLine(currentLine)) {
            throw new NoSuchElementException();
        } else if (hasNextInLine(currentLine, currentPosition)) {
            currentPosition++;
            result = array[currentLine][currentPosition];
        } else {
            currentPosition = -1;
            currentLine++;
            result = next();
        }
        return result;

    }
}
