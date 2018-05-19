package ru.job4j.chess;

/**
 * Шахматная фигура
 * @author AlekseyRomantsov
 * @since 19.05.2018
 * @version 1.0.0.0
 */
public abstract class Figure {
    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Проверка возможности хода фигурой с указанной клетки в указанную клетку
     * @param source - исходная позиция
     * @param dest - желаемая позиция
     * @return - массив промежуточных ячеек
     * @throws ImpossibleMoveException - выбрасывается когда нельзя за 1 ход достичь указанной ячейки
     */
    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Создает копию фигуры в указанной клетке
     * @param dest - клетка куда переместилась фигура
     * @return - ссылка на фигуру
     */
    abstract Figure copy(Cell dest);
}
