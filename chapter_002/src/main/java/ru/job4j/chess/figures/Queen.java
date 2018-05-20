package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;

import java.util.ArrayList;

/**
 * Ферзь
 * @author AlekseyRomantsov
 * @since 20.05.2018
 * @version 1.0.0.0
 */
public class Queen extends Figure {

    public Queen(Cell position) {
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
        if ((Math.abs(dest.x - source.x) != Math.abs(dest.y - source.y)
                && dest.x != source.x && dest.y != source.y) || source.equals(dest)) {
            throw new ImpossibleMoveException();
        }
        ArrayList<Cell> wayList = new ArrayList<>();
        int dx = (dest.x - source.x) > 0 ? 1 : -1;
        int dy = (dest.y - source.y) > 0 ? 1 : -1;
        dx = (dest.x - source.x == 0) ? 0 : dx;
        dy = (dest.y - source.y == 0) ? 0 : dy;

        Cell cell = new Cell(source.x, source.y);
        while (!cell.equals(dest)) {
            cell = new Cell(cell.x + dx, cell.y + dy);
            wayList.add(cell);
        }
        wayList.remove(cell);
        Cell[] way = new Cell[wayList.size()];
        wayList.toArray(way);
        return way;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Queen(dest);
    }
}
