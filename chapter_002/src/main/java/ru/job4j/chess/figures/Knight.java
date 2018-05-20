package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;

/**
 * Конь
 * @author AlekseyRomantsov
 * @since 20.05.2018
 * @version 1.0.0.0
 */
public class Knight extends Figure {

    public Knight(Cell position) {
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
        int dx = Math.abs(dest.x - source.x);
        int dy = Math.abs(dest.y - source.y);

        if (dx + dy != 3 || dx > 2 || dy > 2) {
            throw new ImpossibleMoveException();
        }
        return new Cell[0];
    }

    @Override
    public Figure copy(Cell dest) {
        return new Knight(dest);
    }
}
