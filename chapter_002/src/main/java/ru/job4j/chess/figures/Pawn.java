package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;

import java.util.ArrayList;

/**
 * Пешка
 * @author AlekseyRomantsov
 * @since 20.05.2018
 * @version 1.0.0.0
 */
public class Pawn extends Figure {

    public Pawn(Cell position) {
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
        if (dest.x != source.x || source.equals(dest) || Math.abs(dest.y - source.y) > 2) {
            throw new ImpossibleMoveException();
        }
        ArrayList<Cell> wayList = new ArrayList<>();
        int dy = (dest.y - source.y) > 0 ? 1 : -1;
        int dx = 0;

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
        return new Pawn(dest);
    }
}
