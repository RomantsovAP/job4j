package ru.job4j.tracker;

/**
 * Ввод данных с проверками
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 15.06.2018
 *
 */
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    /**
     * Задает вопрос пользователю и запрашивает строку-ответ
     * @param question - строка с вопросом пользователю
     * @return - строка-ответ пользователя
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Задает вопрос пользователю и запрашивает число-ответ
     * @param question  - строка с вопросом пользователю
     * @param range - перечень допустимых ответов
     * @return - ответ пользователя
     */
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return  value;
    }
}
