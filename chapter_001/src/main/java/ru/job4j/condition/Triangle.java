package ru.job4j.condition;

/**
 * Это класс треугольник, хранит данные о трех своих вершинах и содержит метод для вычисления площади.
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 16.04.2018
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    /**
     * Конструктор, по трем вершинам
     * @param a - первая вершина
     * @param b - вторая
     * @param c - 3-я
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * Формула.
     *
     * (ab + ac + bc) / 2
     *
     * @param ab расстояние между точками a b
     * @param ac расстояние между точками a c
     * @param bc расстояние между точками b c
     * @return Перимент.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2d;
    }

    /**
     * Метод вычисляет площадь треугольника.
     *
     * @return Вернуть прощадь, если треугольник существует или -1, если треугольника нет.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);

        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }


    /**
     * Метод проверяет можно ли построить треугольник с такими длинами сторон.
     * @param ab Длина от точки a b.
     * @param ac Длина от точки a c.
     * @param bc Длина от точки b c.
     * @return - истина, если это треугольник, ложь - если нет
     */
    private boolean exist(double ab, double ac, double bc) {
        boolean isNormTriangle = false;
        if (ab + ac > bc && ab + bc > ac && bc + ac > ab) {
            isNormTriangle = true;
        }

        return isNormTriangle;
    }

}