package ru.job4j.chess;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopTest {
    @Test
    public void whenBishopGoesUpLeft() {
        Bishop bishop = new Bishop(new Cell(5, 8));
        Cell[] result = new Cell[2];
        result[0] = new Cell(4, 7);
        result[1] = new Cell(3, 6);
        assertThat(result, is(bishop.way(bishop.position, new Cell(2, 5))));
    }
    @Test
    public void whenBishopGoesUpRight() {
        Bishop bishop = new Bishop(new Cell(5, 8));
        Cell[] result = new Cell[2];
        result[0] = new Cell(6, 7);
        result[1] = new Cell(7, 6);
        assertThat(result, is(bishop.way(bishop.position, new Cell(8, 5))));
    }
    @Test
    public void whenBishopGoesDownLeft() {
        Bishop bishop = new Bishop(new Cell(5, 1));
        Cell[] result = new Cell[3];
        result[0] = new Cell(4, 2);
        result[1] = new Cell(3, 3);
        result[2] = new Cell(2, 4);
        assertThat(result, is(bishop.way(bishop.position, new Cell(1, 5))));
    }
    @Test
    public void whenBishopGoesDownRight() {
        Bishop bishop = new Bishop(new Cell(2, 6));
        Cell[] result = new Cell[1];
        result[0] = new Cell(3, 7);
        assertThat(result, is(bishop.way(bishop.position, new Cell(4, 8))));
    }

    @Test
    public void whenBishopCAntReachDistanation() {
        Bishop bishop = new Bishop(new Cell(2, 6));
        String result = "no exception";
        try {
            bishop.way(bishop.position, new Cell(2, 5));
        } catch (ImpossibleMoveException e) {
            result = "right exception";
        } catch (RuntimeException e) {
            result = "wrong exception";
        }
        assertThat(result, is("right exception"));
    }


}
