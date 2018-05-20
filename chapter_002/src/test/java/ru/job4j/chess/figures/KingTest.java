package ru.job4j.chess.figures;
import org.junit.Test;
import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.King;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KingTest {
    @Test
    public void whenKingCantReachDistanation() {
        King king = new King(new Cell(5, 3));
        String result = "no exception";
        try {
            king.way(king.position, new Cell(5, 5));
        } catch (ImpossibleMoveException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("right exception"));
    }

    @Test
    public void whenKingCanReachDistanation() {
        King king = new King(new Cell(5, 3));
        String result = "no exception";
        try {
            king.way(king.position, new Cell(4, 2));
            king.way(king.position, new Cell(5, 2));
            king.way(king.position, new Cell(6, 2));
            king.way(king.position, new Cell(6, 3));
            king.way(king.position, new Cell(6, 4));
            king.way(king.position, new Cell(5, 4));
            king.way(king.position, new Cell(4, 4));
            king.way(king.position, new Cell(4, 3));

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
        Figure figure = new King(new Cell(5, 3));
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
