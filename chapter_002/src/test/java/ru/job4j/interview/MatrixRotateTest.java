package ru.job4j.interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class MatrixRotateTest {

    @Test
    public void when2on2matrixthenItRotates() {
        int[][] matrix = new int[2][2];
        matrix[0] = new int[] {1, 2};
        matrix[1] = new int[] {3, 4};

        int[][] answ = new int[2][2];
        answ[0] = new int[] {3, 1};
        answ[1] = new int[] {4, 2};

        MatrixRotate matrixRotate = new MatrixRotate();
        assertThat(answ, is(matrixRotate.rotate(matrix)));
    }

    @Test
    public void when3on3matrixthenItRotates() {
        int[][] matrix = new int[3][3];
        matrix[0] = new int[] {1, 2, 3};
        matrix[1] = new int[] {4, 5, 6};
        matrix[2] = new int[] {7, 8, 9};

        int[][] answ = new int[3][3];
        answ[0] = new int[] {7, 4, 1};
        answ[1] = new int[] {8, 5, 2};
        answ[2] = new int[] {9, 6, 3};

        MatrixRotate matrixRotate = new MatrixRotate();
        assertThat(answ, is(matrixRotate.rotate(matrix)));
    }

    @Test
    public void when3on3Rotates4TimesThenItsTheSameMatrix() {
        int[][] matrix = new int[3][3];
        matrix[0] = new int[] {1, 2, 3};
        matrix[1] = new int[] {4, 5, 6};
        matrix[2] = new int[] {7, 8, 9};

        int[][] answ = new int[3][3];
        answ[0] = new int[] {1, 2, 3};
        answ[1] = new int[] {4, 5, 6};
        answ[2] = new int[] {7, 8, 9};

        MatrixRotate matrixRotate = new MatrixRotate();
        matrix = matrixRotate.rotate(matrix);
        matrix = matrixRotate.rotate(matrix);
        matrix = matrixRotate.rotate(matrix);
        matrix = matrixRotate.rotate(matrix);

        assertThat(answ, is(matrix));
    }

}
