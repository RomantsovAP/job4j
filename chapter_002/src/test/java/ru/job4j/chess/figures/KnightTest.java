package ru.job4j.chess.figures;
import org.junit.Test;
import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.Knight;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KnightTest {
    @Test
    public void whenKnightCantReachDistanation() {
        Knight knight = new Knight(new Cell(5, 3));
        String result = "no exception";
        try {
            knight.way(knight.position, new Cell(5, 5));
        } catch (ImpossibleMoveException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("right exception"));
    }

    @Test
    public void whenKnightCanReachDistanation() {
        Knight knight = new Knight(new Cell(5, 3));
        String result = "no exception";
        try {
            knight.way(knight.position, new Cell(4, 1));
            knight.way(knight.position, new Cell(6, 1));
            knight.way(knight.position, new Cell(7, 2));
            knight.way(knight.position, new Cell(7, 4));
            knight.way(knight.position, new Cell(6, 5));
            knight.way(knight.position, new Cell(4, 5));
            knight.way(knight.position, new Cell(3, 4));
            knight.way(knight.position, new Cell(3, 2));

        } catch (ImpossibleMoveException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("no exception"));
    }

    @Test
    public void whenFigureCantStay() {
        String result = "no exception";
        Figure figure = new Knight(new Cell(5, 3));
        try {
            figure.way(figure.position, figure.position);
        } catch (ImpossibleMoveException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("right exception"));
    }
}