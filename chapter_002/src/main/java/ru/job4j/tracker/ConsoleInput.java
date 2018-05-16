package ru.job4j.tracker;


import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс с реализацией консольного ввода
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 16.05.2018
 */
public class ConsoleInput implements Input {
    Scanner scanner = new Scanner(System.in);

    /**
     * Задает вопрос пользователю и считывает 1 строку ввода данных
     * @param question - строка с вопросом
     * @return - строка с ответом пользователя
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Задает вопрос пользователю и считывает 1 число-ответ
     * @param question - строка с вопросом
     * @param range - диапазон допустимых ответов (например пунктов меню)
     * @return - число-ответ пользователя
     * @throws MenuOutException - пользователь выбрал не подходящее число
     * @throws NumberFormatException - пользователь выбрал не число
     */
    @Override
    public int ask(String question, int[] range) throws MenuOutException, NumberFormatException {
        int itemNumber = Integer.parseInt(this.ask(question));
        if (!this.contains(range, itemNumber)) {
            throw new MenuOutException("Menu out exception ");
        }
        return itemNumber;
    }

    /**
     * Проверяет есть ли в массиве указанное значение
     * @param range - перечень допустимых значений
     * @param itemNumber - проверяемое значение
     * @return - да, если массив содержит указанное значение, нет - если не содержит
     */
    private boolean contains(int[] range, int itemNumber) {
        boolean found = false;
        for (int i = 0; i < range.length && !found; i++) {
            found = range[i] == itemNumber;
        }
        return found;
    }

}
