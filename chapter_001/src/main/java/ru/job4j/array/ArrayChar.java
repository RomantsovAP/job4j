package ru.job4j.array;

/**
 * Обертка над строкой.
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 21.04.2018
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        if (prefix.length() > data.length) {
            result = false;
        } else {
            for (int i = 0; i < prefix.length(); i++) {
                if (data[i] != prefix.charAt(i)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
