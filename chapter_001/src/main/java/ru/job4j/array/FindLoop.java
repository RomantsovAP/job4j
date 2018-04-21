package ru.job4j.array;

/**
 * Класс для поиска значений в массиве прямым перебором элементов
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 21.04.2018
 */
public class FindLoop {
    /**
     * Метод ищет элемент массива, совпадающий по значению
     * @param data - массив с значениями
     * @param el - искомое значение
     * @return - индекс первой позиции массива с совпадающим значением
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                rst = i;
                break;
            }
        }
        return rst;
    }
}
