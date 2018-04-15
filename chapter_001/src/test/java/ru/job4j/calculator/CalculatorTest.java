package ru.job4j.calculator;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test-class for Calculator.
 * @author Romantsov Aleksey
 */
public class CalculatorTest {
    /**
     * 1 + 1 = 2 test.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * 6 / 2 = 3 test.
     */
    @Test
    public void whenDivSixIntoTwoThen() {
        Calculator calc = new Calculator();
        calc.div(6D, 2D);
        double result = calc.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

    /**
     * 6 * 2 = 12 test.
     */
    @Test
    public void whenMultiSixByTwoThen() {
        Calculator calc = new Calculator();
        calc.multiple(6D, 2D);
        double result = calc.getResult();
        double expected = 12D;
        assertThat(result, is(expected));
    }

    /**
     * 6 - 2 = 4 test.
     */
    @Test
    public void whenSubSixByTwoThen() {
        Calculator calc = new Calculator();
        calc.subtract(6D, 2D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }
}