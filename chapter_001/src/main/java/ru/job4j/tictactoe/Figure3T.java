package ru.job4j.tictactoe;

import javafx.scene.shape.Rectangle;

/**
 * Класс для клетки игрового поля
 * @author Petr Arsentev
 * @version 1.0.0.0
 * @since 24.04.18
 */
public class Figure3T extends Rectangle {
    private boolean markX = false;
    private boolean markO = false;

    public Figure3T() {
        //this(false);
    }

    public Figure3T(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    /**
     * Отмечает клетку поля крестиком или ноликом
     * @param markX - true = крести, false = нолик
     */
    public void take(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    public boolean hasMarkX() {
        return this.markX;
    }

    public boolean hasMarkO() {
        return this.markO;
    }
}