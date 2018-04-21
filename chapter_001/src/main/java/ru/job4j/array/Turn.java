package ru.job4j.array;

/**
 * Класс для разворачивания массива
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 21.04.2018
 */
public class Turn {
    /**
     * Метод меняет значения в массиве задом наперед
     * @param array - массив
     * @return - развернутый массив
     */
    public int[] turn(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            array[i] = array[i] + array[array.length - i - 1];
            array[array.length - i - 1] = array[i] - array[array.length - i - 1];
            array[i] = array[i] - array[array.length - i - 1];
        }
        return array;
    }
}