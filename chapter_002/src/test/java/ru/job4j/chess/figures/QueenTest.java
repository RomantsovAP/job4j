package ru.job4j.chess.figures;

import org.junit.Test;
import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.Queen;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueenTest {
    @Test
    public void whenQueenGoesUp() {
        Queen queen = new Queen(new Cell(5, 8));
        Cell[] result = new Cell[2];
        result[0] = new Cell(5, 7);
        result[1] = new Cell(5, 6);
        assertThat(result, is(queen.way(queen.position, new Cell(5, 5))));
    }

    @Test
    public void whenQueenGoesDown() {
        Queen queen = new Queen(new Cell(5, 3));
        Cell[] result = new Cell[1];
        result[0] = new Cell(5, 4);
        assertThat(result, is(queen.way(queen.position, new Cell(5, 5))));
    }

    @Test
    public void whenQueenGoesLeft() {
        Queen queen = new Queen(new Cell(5, 3));
        Cell[] result = new Cell[3];
        result[0] = new Cell(4, 3);
        result[1] = new Cell(3, 3);
        result[2] = new Cell(2, 3);
        assertThat(result, is(queen.way(queen.position, new Cell(1, 3))));
    }

    @Test
    public void whenQueenGoesRight() {
        Queen queen = new Queen(new Cell(5, 3));
        Cell[] result = new Cell[2];
        result[0] = new Cell(6, 3);
        result[1] = new Cell(7, 3);
        assertThat(result, is(queen.way(queen.position, new Cell(8, 3))));
    }

    @Test
    public void whenQueenCantReachDistanation() {
        Queen queen = new Queen(new Cell(5, 3));
        String result = "no exception";
        try {
            queen.way(queen.position, new Cell(3, 2));
        } catch (ImpossibleMoveException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("right exception"));
    }

    @Test
    public void whenQueenGoesUpLeft() {
        Queen queen = new Queen(new Cell(5, 8));
        Cell[] result = new Cell[2];
        result[0] = new Cell(4, 7);
        result[1] = new Cell(3, 6);
        assertThat(result, is(queen.way(queen.position, new Cell(2, 5))));
    }
    @Test
    public void whenQueenGoesUpRight() {
        Queen queen = new Queen(new Cell(5, 8));
        Cell[] result = new Cell[2];
        result[0] = new Cell(6, 7);
        result[1] = new Cell(7, 6);
        assertThat(result, is(queen.way(queen.position, new Cell(8, 5))));
    }
    @Test
    public void whenQueenGoesDownLeft() {
        Queen queen = new Queen(new Cell(5, 1));
        Cell[] result = new Cell[3];
        result[0] = new Cell(4, 2);
        result[1] = new Cell(3, 3);
        result[2] = new Cell(2, 4);
        assertThat(result, is(queen.way(queen.position, new Cell(1, 5))));
    }
    @Test
    public void whenQueenGoesDownRight() {
        Queen queen = new Queen(new Cell(2, 6));
        Cell[] result = new Cell[1];
        result[0] = new Cell(3, 7);
        assertThat(result, is(queen.way(queen.position, new Cell(4, 8))));
    }

    @Test
    public void whenFigureCantStay() {
        String result = "no exception";
        Figure figure = new Queen(new Cell(5, 3));
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
