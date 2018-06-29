package ru.job4j.interview;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * Считаем и проверям скобки в выражении используя стек
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 29.06.2018
 */
public class Brackets {

    /**
     * Индексы открывающих и закрывающих скобок должны совпадать по видам
     */
    private static final char[] OPEN_BRACKETS = {'(', '[', '{'};
    private static final char[] CLOSE_BRACKETS = {')', ']', '}'};

    /**
     * Скобка исходной строки
     */
    public static class Bracket {
        public int position;
        public char bracket;

        /**
         * @param position - индекс позиции в исходной строке
         * @param bracket - символ скобки
         */
        public Bracket(int position, char bracket) {
            this.position = position;
            this.bracket = bracket;
        }

        @Override
        public String toString() {
            return "#pos=" + position + "=" + bracket + "# ";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Bracket bracket1 = (Bracket) o;
            return position == bracket1.position && bracket == bracket1.bracket;
        }

        @Override
        public int hashCode() {

            return Objects.hash(position, bracket);
        }
    }

    /**
     * Разбираем выражение со скобками
     * если подать строку с непарными скобками или с неправильным вложением скобок друг в друга - выдаст ошибку
     * @param expression - строка-выражение содержащая скобки
     * @return - список скобок, парами, сначала открывающая, потом закрывающая
     */
    public ArrayList<Bracket> parseBrackets(String expression) {
        boolean answ = true;
        ArrayList<Character> open = new ArrayList<>();
        ArrayList<Character> close = new ArrayList<>();
        ArrayList<Bracket> brackets = new ArrayList<>();

        for (int i = 0; i < OPEN_BRACKETS.length; i++) {
            open.add(OPEN_BRACKETS[i]);
            close.add(CLOSE_BRACKETS[i]);
        }
        Stack<Integer> openedBrackets = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (!open.contains(current) && !close.contains(current)) {
                continue;
            }
            if (open.contains(current)) {
                openedBrackets.push(i);
            }
            if (close.contains(current)) {
                if (openedBrackets.isEmpty()) {
                    answ = false;
                } else {
                    int openBracketIndex = openedBrackets.pop();
                    char openBracket = expression.charAt(openBracketIndex);

                    if (open.indexOf(openBracket) != close.indexOf(current)) {
                        answ = false;
                    } else {
                        brackets.add(new Bracket(openBracketIndex, openBracket));
                        brackets.add(new Bracket(i, current));
                    }
                }
            }
            if (!answ) {
                break;
            }
        }
        if (!answ) {
            throw new IllegalArgumentException("It's wrong expression!");
        }
        return brackets;
    }

}
