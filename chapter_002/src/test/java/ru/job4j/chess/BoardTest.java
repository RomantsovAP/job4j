package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    @Test
    public void whenBishopCanMove() {
        Board board = new Board();
        board.add(new Bishop(new Cell(1, 1)));
        board.add(new Bishop(new Cell(1, 2)));
        board.add(new Bishop(new Cell(2, 1)));
        assertThat(true, is(board.move(new Cell(1, 1), new Cell(8, 8))));
    }

    @Test
    public void whenBishopCantMoveOtherFigureOnTheWay() {
        String result = "no exception";
        Board board = new Board();
        board.add(new Bishop(new Cell(1, 1)));
        board.add(new Bishop(new Cell(2, 2)));
        try {
            board.move(new Cell(1, 1), new Cell(8, 8));
        } catch (OccupiedWayException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("right exception"));
    }

    @Test
    public void whenBishopCantMoveNoBishopOnTheCell() {
        String result = "no exception";
        Board board = new Board();
        board.add(new Bishop(new Cell(1, 1)));
        board.add(new Bishop(new Cell(2, 2)));
        try {
            board.move(new Cell(5, 5), new Cell(8, 8));
        } catch (FigureNotFoundException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("right exception"));
    }

    @Test
    public void whenBishopCantMoveDistanationIsUnreachable() {
        String result = "no exception";
        Board board = new Board();
        board.add(new Bishop(new Cell(1, 1)));
        board.add(new Bishop(new Cell(2, 1)));
        try {
            board.move(new Cell(1, 1), new Cell(8, 7));
        } catch (ImpossibleMoveException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("right exception"));
    }

}
