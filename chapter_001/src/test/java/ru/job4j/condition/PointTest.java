package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test-class for Point-class.
 * @author Romantsov Aleksey
 */
public class PointTest {

    /**
     * distance from (0,0) to (3,4) = 5
     */
    @Test
    public void whenFrom0to34Then5() {
        Point point = new Point(0, 0);
        double result = point.distanceTo(new Point(3, 4));
        double expected = 5D;
        assertThat(result, is(expected));
    }

    /**
     * distance from (0,0) to (-3,-4) = 5
     */
    @Test
    public void whenFrom0toMinus34Then5() {
        Point point = new Point(0, 0);
        double result = point.distanceTo(new Point(-3, -4));
        double expected = 5D;
        assertThat(result, is(expected));
    }


}