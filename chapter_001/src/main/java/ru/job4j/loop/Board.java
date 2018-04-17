package ru.job4j.loop;

/**
 * Класс для рисования шахматной доски в псевдографике
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 17.04.2018
 */
public class Board {
    /**
     * метод рисует шахматную доску из пробелов и симолов х
     * @param width - ширина
     * @param height - высота
     * @return - тект с шахматной доской
     */
    public String paint(int width, int height) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {
                sb.append((j % 2 == i % 2) ? 'X' : ' ');
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
