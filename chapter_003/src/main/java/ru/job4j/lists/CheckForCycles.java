package ru.job4j.lists;

import java.util.ArrayList;

/**
 * Ищем циклы или петли в связном списке
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 14.07.2018
 */
public class CheckForCycles {
    class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * Проверяет, есть ли циклы или петли в связном списке
     * @param first - ссылка на начало связного списка
     * @return да, если есть циклы или петли
     */
    public boolean hasCycle(Node first) {
        ArrayList<Node> arr = new ArrayList<>();
        Node curentNode = first;
        while (curentNode != null && !arr.contains(curentNode)) {
            arr.add(curentNode);
            curentNode = curentNode.next;
        }
        return (curentNode != null);
    }

    private Node getNode(int index, Node root) {
        Node result = root;
        int i = 0;
        while (i < index && result != null) {
            result = result.next;
            i++;
        }
        return result;
    }

    /**
     * Проверяет есть ли циклы в связном списке, без использования коллекций
     * @param first - начало списка
     * @return - да, если циклы есть
     */
    public boolean hasCycleMk2(Node first) {
        boolean result = first == null;
        boolean endIsReached = first == null;
        int i = 0;
        while (!result) {

            Node firstNode  = getNode(i, first);

            if (firstNode == null) {
                endIsReached = true;
                break;
            }

            if (firstNode.next == firstNode) {
                break;
            }

            for (int j = 0; j < i - 1; j++) {

                Node lastNode = getNode(j, first);
                if (lastNode.next == firstNode) {
                    result = true;
                }
            }
            i++;
        }
        return !endIsReached;
    }

    public static void main(String[] args) {
        CheckForCycles check = new CheckForCycles();
        check.hasCycleMk2(check.new Node<>(1));
    }

}
