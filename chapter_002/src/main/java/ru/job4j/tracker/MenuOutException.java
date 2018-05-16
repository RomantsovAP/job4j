package ru.job4j.tracker;

/**
 * Исключение - указан отсутствующий пункт меню
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 16.05.2018
 */
public class MenuOutException extends RuntimeException {
    public MenuOutException(String message) {
        super(message);
    }
}
