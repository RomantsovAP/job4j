package ru.job4j.chess.figures;

import org.junit.Test;
import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.Rook;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RookTest {
    @Test
    public void whenRookGoesUp() {
        Rook rook = new Rook(new Cell(5, 8));
        Cell[] result = new Cell[2];
        result[0] = new Cell(5, 7);
        result[1] = new Cell(5, 6);
        assertThat(result, is(rook.way(rook.position, new Cell(5, 5))));
    }

    @Test
    public void whenRookGoesDown() {
        Rook rook = new Rook(new Cell(5, 3));
        Cell[] result = new Cell[1];
        result[0] = new Cell(5, 4);
        assertThat(result, is(rook.way(rook.position, new Cell(5, 5))));
    }

    @Test
    public void whenRookGoesLeft() {
        Rook rook = new Rook(new Cell(5, 3));
        Cell[] result = new Cell[3];
        result[0] = new Cell(4, 3);
        result[1] = new Cell(3, 3);
        result[2] = new Cell(2, 3);
        assertThat(result, is(rook.way(rook.position, new Cell(1, 3))));
    }

    @Test
    public void whenRookGoesRight() {
        Rook rook = new Rook(new Cell(5, 3));
        Cell[] result = new Cell[2];
        result[0] = new Cell(6, 3);
        result[1] = new Cell(7, 3);
        assertThat(result, is(rook.way(rook.position, new Cell(8, 3))));
    }

    @Test
    public void whenRookCantReachDistanation() {
        Rook rook = new Rook(new Cell(5, 3));
        String result = "no exception";
        try {
            rook.way(rook.position, new Cell(4, 2));
        } catch (ImpossibleMoveException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("right exception"));
    }

    @Test
    public void whenFigureCantStay() {
        String result = "no exception";
        Figure figure = new Rook(new Cell(5, 3));
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
