package ru.job4j.chess;

import ru.job4j.chess.figures.Figure;

/**
 * Шахматная доска, поле
 * @author AlekseyRomantsov
 * @since 19.05.2018
 * @version 1.0.0.0
 */
public class Board {
    private Figure[] figures = new Figure[32];
    private int count = 0;

    public void add(Figure figure) {
        figures[count++] = figure;
    }

    private int getFigure(Cell cell) {
        for (int i = 0; i < count; i++) {
            if (figures[i].position.equals(cell)) {
                return i;
            }
        }
        return -1;
    }

    private int getFigureV2(Cell cell) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (figures[i].position.equals(cell)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private boolean isWayClear(Cell[] way) {
        boolean clear = true;
        for (int i = 0; i < way.length; i++) {
            if (getFigure(way[i]) >= 0) {
                clear = false;
                break;
            }
        }
        return clear;
    }


    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        /*
        - Что в заданной ячейки есть фигура. если нет. то выкинуть исключение
        - Если фигура есть. Проверить может ли она так двигаться. Если нет то упадет исключение
        - Проверить что полученный путь. не занят фигурами. Если занят выкинуть исключение
        - Если все отлично. Записать в ячейку новое новое положение Figure figure.copy(Cell dest)
        */
        int figure = getFigure(source);
        if (figure == -1) {
            throw new FigureNotFoundException();
        }
        if (getFigure(dest) >= 0 || !isWayClear(figures[figure].way(source, dest))) {
            throw new OccupiedWayException();
        }
        figures[figure] = figures[figure].copy(dest);
        return true;
    }

}
