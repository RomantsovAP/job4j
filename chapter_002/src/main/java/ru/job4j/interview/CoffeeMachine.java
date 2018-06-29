package ru.job4j.interview;

import java.util.Arrays;

/**
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 29.06.2018
 */
public class CoffeeMachine {

    private static final int CHANGE_MAX_COINS_COUNT = 100;
    /**
     * Набор доступных монет, порядок важен, начинаем с наибольшего номинала!
     */
    private static final int[] COINS = {10, 5, 2, 1};

    /**
     * Метод возвращает минимальный набор монет для сдачи с купюры при покупке кофе
     * @param value - внесенная сумма, чаще всего 1 купюра
     * @param price - цена стаканчика кофе
     * @return - минимальный набор монеи
     */
    public int[] changes(int value, int price) {
        int[] answ = new int[CoffeeMachine.CHANGE_MAX_COINS_COUNT];
        int count = 0;

        int rest = value - price;
        for (int coin:COINS) {
            while (rest >= coin) {
                rest -= coin;
                answ[count++] = coin;
            }
        }
        return Arrays.copyOf(answ, count);
    }

}
