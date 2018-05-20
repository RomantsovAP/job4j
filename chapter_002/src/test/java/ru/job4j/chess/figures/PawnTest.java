package ru.job4j.chess.figures;
import org.junit.Test;
import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PawnTest {

    @Test
    public void whenPawnCantReachDistanation() {
        Pawn pawn = new Pawn(new Cell(5, 3));
        String result = "no exception";
        try {
            pawn.way(pawn.position, new Cell(5, 6));
        } catch (ImpossibleMoveException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("right exception"));
    }

    @Test
    public void whenPawnGoesUp() {
        Pawn pawn = new Pawn(new Cell(5, 5));
        Cell[] result = new Cell[1];
        result[0] = new Cell(5, 6);
        assertThat(result, is(pawn.way(pawn.position, new Cell(5, 7))));
    }

    @Test
    public void whenPawnGoes1Cell() {
        Pawn pawn = new Pawn(new Cell(5, 5));
        Cell[] result = new Cell[0];
        assertThat(result, is(pawn.way(pawn.position, new Cell(5, 6))));
        assertThat(result, is(pawn.way(pawn.position, new Cell(5, 4))));
    }

    @Test
    public void whenPawnGoesDown() {
        Pawn pawn = new Pawn(new Cell(5, 5));
        Cell[] result = new Cell[1];
        result[0] = new Cell(5, 4);
        assertThat(result, is(pawn.way(pawn.position, new Cell(5, 3))));
    }


    @Test
    public void whenFigureCantStay() {
        String result = "no exception";
        Figure figure = new Pawn(new Cell(5, 3));
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
