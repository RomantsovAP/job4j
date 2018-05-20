package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;

/**
 * Король
 * @author AlekseyRomantsov
 * @since 20.05.2018
 * @version 1.0.0.0
 */
public class King extends Figure {

    public King(Cell position) {
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
        if (Math.abs(dest.x - source.x) > 1 || Math.abs(dest.y - source.y) > 1 || source.equals(dest)) {
            throw new ImpossibleMoveException();
        }
        return new Cell[0];
    }

    @Override
    public Figure copy(Cell dest) {
        return new King(dest);
    }
}
