package ru.job4j.sort;

/**
 * Слияние массивов
 * @author AlekseyRomantsov
 * @since 06.06.2018
 * @version 1.0.0.0
 */
public class ArraysMerge {
    /**\
     * Производит слияние двух отсортированных по возрастанию массивов
     * @param a - отсортированный по возрастанию массив
     * @param b - отсортированный по возрастанию массив
     * @return - отсортированный массив, полученный слиянием
     */
    public int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0;
        while (i + j < a.length + b.length) {
            if (i < a.length && j < b.length) {
                result[i + j] = (a[i] > b[j]) ? b[j++] : a[i++];
            } else if (i == a.length) {
                result[i + j] = b[j++];
            } else if (j == b.length) {
                result[i + j] = a[i++];
            }
        }
        return result;
    }
}
