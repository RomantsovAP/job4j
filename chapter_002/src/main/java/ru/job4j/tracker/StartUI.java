package ru.job4j.tracker;

/**
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 01.05.2018
 */
public class StartUI {

    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Запуск меняю
     */
    public void init() {
        MenuTracker menu = new MenuTracker(tracker, input);
        menu.run();
    }

    /**
     * Запускт программы.
     * @param args - параметры из консоли
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}