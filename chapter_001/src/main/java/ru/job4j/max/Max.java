package ru.job4j.max;

/**
 * Класс для вычисления максимума чисед
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 16.04.2018
 */
public class Max {

    public int max(int first, int second) {
        return first > second ? first : second;
    }

    public int max(int first, int second, int third) {
        return max(first, max(second, third));
    }
}
