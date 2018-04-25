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

    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);
        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками А и В : " + result);
    }
}
