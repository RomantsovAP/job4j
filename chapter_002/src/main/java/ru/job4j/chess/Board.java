package ru.job4j.chess;

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

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        /*
        - Что в заданной ячейки есть фигура. если нет. то выкинуть исключение
        - Если фигура есть. Проверить может ли она так двигаться. Если нет то упадет исключение
        - Проверить что полученный путь. не занят фигурами. Если занят выкинуть исключение
        - Если все отлично. Записать в ячейку новое новое положение Figure figure.copy(Cell dest)
        */
        Figure figure = null;
        int figureIndex;
        for (figureIndex = 0; figureIndex < count; figureIndex++) {
            if (figures[figureIndex].position.equals(source)) {
                figure = figures[figureIndex];
                break;
            }
        }
        if (figure == null) {
            throw new FigureNotFoundException();
        }
        for (int i = 0; i < count; i++) {
            if (figures[i].position.equals(dest)) {
                throw new OccupiedWayException();
            }
        }
        Cell[] way = figure.way(source, dest);
        for (int i = 0; i < way.length; i++) {
            for (int j = 0; j < count; j++) {
                if (figures[j].position.equals(way[i])) {
                    throw new OccupiedWayException();
                }
            }
        }
        figures[figureIndex] = figure.copy(dest);
        return true;
    }


}
