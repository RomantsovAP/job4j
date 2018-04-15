package ru.job4j.calculator;

/**
 * Корвертор валюты.
 */
public class Converter {

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return value / 70;
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллоры
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }

    /**
     * Ковертируем доллары в рубли.
     * @param value доллары
     * @return рубли
     */
    public int dollarToRuble(int value) {
        return value * 60;
    }

    /**
     * Ковертируем евро в рубли.
     * @param value евро
     * @return рубли
     */
    public int euroToRubble(int value) {
        return value * 70;
    }
}