package ru.job4j.condition;

/**
 * @author AlekseyRomantsov
 * Class for storing information about coordinates of point
 * @version 1.0.0.0
 * @since 15.04.2018
 */
public class Point {
    private int x;
    private int y;

    /**
     * Конструктор
     * @param x - X coordinate
     * @param y - Y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method for calculating distance from our point to point-param
     * @param that  - second point
     * @return distance between out point and param
     */
    public double distanceTo(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }
}
