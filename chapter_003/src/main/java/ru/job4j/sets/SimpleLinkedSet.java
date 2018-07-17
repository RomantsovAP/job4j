package ru.job4j.sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedSet<T> implements Iterable<T> {
    private class Node {
        T value;
        Node next;
    }

    private Node root = new Node();

    public void add(T newElem) {
        boolean found = false;
        Node check = root;
        while (check.next != null) {
            check = check.next;
            if (check.value.equals(newElem)) {
                found = true;
                break;
            }
        }
        if (!found) {
            Node node = new Node();
            node.value = newElem;
            if (root.next == null) {
                root.next = node;
            } else {
                node.next = root.next;
                root.next = node;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node currentNode = root;
            @Override
            public boolean hasNext() {
                return currentNode.next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                currentNode = currentNode.next;
                return currentNode.value;
            }
        };
    }
}
