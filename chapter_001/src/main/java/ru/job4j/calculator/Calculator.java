package ru.job4j.calculator;

/**
 This is simple Calculator.
 @author Romantsov Aleksey
 */
public class Calculator {
    private double result;

    /**
     * sum-operation.
     * @param first - first argument for sum
     * @param second - second argument fo sum
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * substract-operation.
     * @param first - minuend
     * @param second - subtrahend
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * division-operation.
     * @param first - dividend
     * @param second - divisor
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * multiplication-operation.
     * @param first - multiplicand
     * @param second - multiplier
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Getter for result
     * @return - the result of last operation
     */
    public double getResult() {
        return this.result;
    }
}

