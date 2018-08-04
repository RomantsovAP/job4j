package ru.job4j.tree;

import java.util.*;

/**
 * Дерево
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 04.08.2018
 * @param <E> - тип хранимых значений
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Добавляет новое значение в дерево
     * @param parent parent - значение родительского элемента
     * @param child child - значение дочернего элемента
     * @return - истина, если добавление произошло
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = true;
        Optional<Node<E>> nodeParent = findBy(parent);
        Optional<Node<E>> nodeChild = findBy(child);
        if (nodeParent.isPresent() && !nodeChild.isPresent()) {
            nodeParent.get().add(new Node<>(child));
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Поиск узла дерева по хранимому значению
     * @param value - значение
     * @return - найденный узел
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Queue<Node<E>> queue = new LinkedList<>();
            {
                queue.offer(root);
            }
            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> el = queue.poll();
                for (Node<E> child : el.leaves()) {
                    queue.offer(child);
                }
                return el.getValue();
            }
        };
    }

    /**
     * Проверяет является ли дерево бинарным (не более двух дочерних элементов у любого элемента дерева)
     * @return - истина, если дерево бинарное
     */
    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty() && result) {
            Node<E> currNode = queue.poll();
            if (currNode.leaves().size() > 2) {
                result = false;
            } else {
                for (Node<E> child : currNode.leaves()) {
                    queue.offer(child);
                }
            }
        }
        return result;
    }

}
