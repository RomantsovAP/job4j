package ru.job4j.chess;

import java.util.ArrayList;

/**
 * Слон
 * @author AlekseyRomantsov
 * @since 19.05.2018
 * @version 1.0.0.0
 */
public class Bishop extends Figure {

    public Bishop(Cell position) {
        super(position);
    }

    /**
     * Ход фигурой
     * @param source - исходная позиция
     * @param dest - желаемая позиция
     * @return - массив с промежуточными клетками
     * @throws ImpossibleMoveException - ход невозможен
     */
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (Math.abs(dest.x - source.x) != Math.abs(dest.y - source.y)) {
            throw new ImpossibleMoveException();
        }
        ArrayList<Cell> wayList = new ArrayList<>();
        int dx = (dest.x - source.x) > 0 ? 1 : -1;
        int dy = (dest.y - source.y) > 0 ? 1 : -1;
        Cell currentCell = new Cell(source.x, source.y);
        while (!currentCell.equals(dest)) {
            currentCell = new Cell(currentCell.x + dx, currentCell.y + dy);
            wayList.add(currentCell);
        }
        wayList.remove(currentCell);
        Cell[] way = new Cell[wayList.size()];
        wayList.toArray(way);
        return way;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }
}
