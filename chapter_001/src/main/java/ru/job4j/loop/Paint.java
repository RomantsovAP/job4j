package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * класс для рисования пирамид заданной высоты в псевдографике.
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 17.04.2018
 */
public class Paint {


    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * Метод рисует пирамиду заданной высоты в псевдографике
     * это мой вариант решения, надеюсь не помешает проверке.
     * @param height - высота пирамиды
     * @return - строку с рисунком пирамиды в псевдографике
     */
    public String pyramid2(int height) {
        StringBuilder screen = new StringBuilder();
        StringBuilder line = new StringBuilder();
        int weight = 2 * height - 1;
        for (int i = 0; i < weight; i++) {
            line.append('^');
        }
        screen.append(line.toString() + System.lineSeparator());
        for (int i = 1; i < height; i++) {
            line.replace(i - 1, i, " ");
            line.reverse();
            line.replace(i - 1, i, " ");
            screen.insert(0, line.toString() + System.lineSeparator());
        }
        return screen.toString();
    }

}
