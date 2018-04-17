package ru.job4j.loop;

/**
 * класс для тетирования циклов.
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 17.04.2018
 */
public class Counter {
    /**
     * Метод вычисляет сумму четныx чисел в диапазоне от start до finish.
     * @param start - начало интервала
     * @param finish - конец интервала
     * @return - сумма четных чисел
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result += i;
            }
        }
        return result;
    }
}
