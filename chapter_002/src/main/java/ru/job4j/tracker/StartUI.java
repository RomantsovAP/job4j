package ru.job4j.tracker;

/**
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 01.05.2018
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для вывода всех заявок
     */
    private static final String SHOW_ALL = "1";
    /**
     * Константа меню для редактирования заявки
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для поиска заявки по идентификатору
     */
    private static final String FIND_BY_ID = "4";
    /**
     * Константа меню для поиска заявок по имени
     */
    private static final String FIND_BY_NAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
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
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findItemById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findItemsByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Поиск всех заявок по имени
     */
    private void findItemsByName() {
        String name = this.input.ask("Введите наименование заявки");
        Item[] foundItems = this.tracker.findByName(name);
        if (foundItems.length == 0) {
            System.out.println("Заявок не найдено");
        } else {
            for (Item currItem : foundItems) {
                System.out.println(currItem);
            }
        }

    }

    /**
     * Поиск заявки по id
     */
    private void findItemById() {
        String id = this.input.ask("Введите id заявки для поиска");
        Item foundItem = tracker.findById(id);
        if (foundItem != null) {
            System.out.println(foundItem);
        } else {
            System.out.println("Заявки с таким id не найдено");
        }
    }

    /**
     * Удаление заявки
     */
    private void deleteItem() {
        String id = this.input.ask("Введите id заявки для удаления");
        Item foundItem = tracker.findById(id);
        if (foundItem != null) {
            String confirmation = this.input.ask("Будет удалена заявка " + foundItem.toString() + " , вы уверены? (y/n)");
            if ("y".equals(confirmation)) {
                tracker.delete(id);
            }
        } else {
            System.out.println("Заявки с таким id не найдено");
            this.showAllItems();
        }
    }

    /**
     * Отображает полный список заявок
     */
    private void showAllItems() {
        System.out.println("------------ Полный список заявок --------------");
        Item[] items = tracker.findAll();
        for (Item curItem:items) {
            System.out.println(curItem);
        }
    }

    /**
     * редактирование заявки
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        String id = this.input.ask("Введите id заявки :");
        Item foundItem = tracker.findById(id);
        if (foundItem != null) {
            String name = this.input.ask("Введите новое имя заявки :");
            String desc = this.input.ask("Введите новое описание заявки :");
            Item editedItem = new Item(name, desc);
            this.tracker.replace(id, editedItem);
            System.out.println("------------ заявка отредактирована -----------");
        } else {
            System.out.println("Заявки с таким id не найдено");
            this.showAllItems();
        }

    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Отображает меняю пользователю
     */
    private void showMenu() {
        System.out.println("****************");
        System.out.println("Меню.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Запускт программы.
     * @param args - параметры из консоли
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}