package ru.job4j.loop;

/**
 * класс для рисования пирамид заданной высоты в псевдографике.
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 17.04.2018
 */
public class Paint {

    public String rightTrl(int height) {
        // Буфер для результата.
        StringBuilder screen = new StringBuilder();
        // ширина будет равна высоте.
        int weight = height;
        // внешний цикл двигается по строкам.
        for (int row = 0; row != height; row++) {
            // внутренний цикл определяет положение ячейки в строке.
            for (int column = 0; column != weight; column++) {
                // если строка равна ячейки, то рисуем галку.
                // в данном случае строка определяем, сколько галок будет на строке
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод строки.
            screen.append(System.lineSeparator());
        }
        // Получаем результат.
        return screen.toString();
    }

    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= weight - column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     * Метод рисует пирамиду заданной высоты в псевдографике
     * это мой вариант решения, надеюсь не помешает проверке.
     * @param height - высота пирамиды
     * @return - строку с рисунком пирамиды в псевдографике
     */
    public String pyramid2(int height) {
        StringBuilder screen = new StringBuilder();
        StringBuilder line = new StringBuilder(); //основание пирамиды
        int weight = 2 * height - 1;
        for (int i = 0; i < weight; i++) {
            line.append('^');
        }
        screen.append(line.toString() + System.lineSeparator());

        for (int i = 1; i < height; i++) {
            line.replace(i - 1, i, " "); // заменим i-ый на пробел
            line.reverse();
            line.replace(i - 1, i, " "); // заменим i-ый c конца на пробел
            screen.insert(0, line.toString() + System.lineSeparator());

        }
        return screen.toString();
    }

}
