package ru.job4j.tictactoe;

/**
 * Класс с логикой игры в крестики-нолики
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 24.04.2018
 */
public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    private boolean hasMark(Figure3T figure3T, int mark) {
        boolean result;
        if (mark == 1) {
            result = figure3T.hasMarkX();
        } else {
            result = figure3T.hasMarkO();
        }
        return result;
    }

    private boolean isWinner(int mark) {
        boolean result = false;
        int[] rows = new int[table.length];
        int[] columns = new int[table.length];
        int diag1 = 0;
        int diag2 = 0;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (hasMark(table[i][j], mark)) {
                    rows[i]++;
                    columns[j]++;
                    if (i == j) {
                        diag1++;
                    }
                    if (i == table.length - j - 1) {
                        diag2++;
                    }
                    if (diag1 == 3 || diag2 == 3 || rows[i] == 3 || columns[j] == 3) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Победили крестики
     * @return - true, если победили крестики, false - если нет
     */
    public boolean isWinnerX() {
        return isWinner(1);
    }

    /**
     * Победили нолики
     * @return - true, если победили нолики, false - если нет
     */
    public boolean isWinnerO() {
        return isWinner(2);
    }

    /**
     * Определяет, есть ли свободные клетки для хода
     * @return - можно kb ходить дальше
     */
    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (!table[i][j].hasMarkO() && !table[i][j].hasMarkX()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}