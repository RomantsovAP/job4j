package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Меню пользователя задачника
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 16.05.2018
  */
public class MenuTracker {
    private List<UserAction> actions = new ArrayList<>();
    private ITracker tracker;
    private Input input;
    private boolean exit;

    /**
     * Создание меню с заполнением возможных действий пользователя
     * @param tracker - задачник
     * @param input - один из вариантов ввода данных
     */
    public MenuTracker(ITracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
        this.exit = false;
        fillActions();
    }

    private void showMenu() {
        System.out.println("****************");
        System.out.println("Меню.");
        for (UserAction action : actions) {
            System.out.println(action.showInfo());
        }
    }

    private void fillActions() {
        actions.add(new CreateItem());
        actions.add(new ShowAllItems());
        actions.add(new EditItem());
        actions.add(new DeleteItem());
        actions.add(new FindItemById());
        actions.add(new FindItemsByName());
        actions.add(new ExitMenu());
    }

    private int[] fillMenuRange() {
        int[] range = new int[this.actions.size()];
        for (int i = 0; i < range.length; i++) {
            range[i] = this.actions.get(i).key();
        }
        return range;
    }

    /**
     * Основной метод - запрашивает и отрабатывает выбор пунктов меню в цилке
     */
    public void run() {
        while (!exit) {
            this.showMenu();
            int numberOfAction = this.input.ask("Введите пункт меню : ", fillMenuRange());
            actions.get(numberOfAction).execute(input, tracker);
        }
    }

    /**
     * Действие пользователя - создать новый элемент-задачу
     */
    private class CreateItem extends BaseAction {

        public CreateItem() {
            super(0, "Add new Item");
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }

    }

    /**
     * Действие пользователя - показать все задачи
     */
    private static class ShowAllItems extends BaseAction {
        public ShowAllItems() {
            super(1, "Show all items");
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Полный список заявок --------------");
            List<Item> items = tracker.findAll();
            for (Item curItem : items) {
                System.out.println(curItem);
            }
        }
    }

    /**
     * Действие пользователя - отредактировать элемент-задачу
     */
    private class EditItem extends BaseAction {
        public EditItem() {
            super(2, "Edit item");
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Редактирование заявки --------------");
            String id = input.ask("Введите id заявки :");
            Item foundItem = tracker.findById(id);
            if (foundItem != Tracker.NULL_ITEM) {
                String name = input.ask("Введите новое имя заявки :");
                String desc = input.ask("Введите новое описание заявки :");
                Item editedItem = new Item(name, desc);
                tracker.replace(id, editedItem);
                System.out.println("------------ заявка отредактирована -----------");
            } else {
                System.out.println("Заявки с таким id не найдено");
            }
        }
    }

    /**
     * Действие пользователя - найти элемент по известному id
     */
    private class FindItemById extends BaseAction {
        public FindItemById() {
            super(4, "Find item by id");
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            String id = input.ask("Введите id заявки для поиска");
            Item foundItem = tracker.findById(id);
            if (foundItem != null) {
                System.out.println(foundItem);
            } else {
                System.out.println("Заявки с таким id не найдено");
            }
        }
    }

    /**
     * Действие пользователя - найти элементы-задачи по наименованию
     */
    private class FindItemsByName extends BaseAction {
        public FindItemsByName() {
            super(5, "Find items by name");
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            String name = input.ask("Введите наименование заявки");
            List<Item> foundItems = tracker.findByName(name);
            if (foundItems.size() == 0) {
                System.out.println("Заявок не найдено");
            } else {
                for (Item currItem : foundItems) {
                    System.out.println(currItem);
                }
            }
        }
    }

    /**
     * Действие пользователя - выход, завершить работу с меню и задачником
     */
    private class ExitMenu extends BaseAction {
        public ExitMenu() {
            super(6, "Exit Program");
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            exit = true;
        }
    }

}

/**
 * Действие пользователя - удалить задачу
 */
class DeleteItem extends BaseAction {
    public DeleteItem() {
        super(3, "Delete item");
    }

    @Override
    public void execute(Input input, ITracker tracker) {
        String id = input.ask("Введите id заявки для удаления");
        Item foundItem = tracker.findById(id);
        if (foundItem != Tracker.NULL_ITEM) {
            String confirmation = input.ask("Будет удалена заявка " + foundItem.toString() + " , вы уверены? (y/n)");
            if ("y".equals(confirmation)) {
                tracker.delete(id);
            }
        } else {
            System.out.println("Заявки с таким id не найдено");
        }
    }
}