package ru.job4j.condition;

/**
 * @author Aleksey Romantsov
 * @version 1.0.0.0
 * @since 16.04.2018
 */
public class DummyBot {
    /**
     * Отвечает на вопросы.
     * @param question Вопрос от клиента.
     * @return Ответ.
     */
    public String answer(String question) {
        String result = "Это ставит меня в тупик. Спросите другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            result = "Привет, умник.";
        } else if ("Пока.".equals(question)) {
            result = "До скорой встречи.";
        }
        return result;
    }
}
