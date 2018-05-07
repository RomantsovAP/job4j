package ru.job4j.tracker;

public class MenuTracker {
    private UserAction[] actions = new UserAction[7];
    private Tracker tracker;
    private Input input;
    private boolean exit;

    public MenuTracker(Tracker tracker, Input input) {
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
        actions[0] = new CreateItem();
        actions[1] = new ShowAllItems();
        actions[2] = new EditItem();
        actions[3] = new DeleteItem();
        actions[4] = new FindItemById();
        actions[5] = new FindItemsByName();
        actions[6] = new ExitMenu();
    }

    private int[] fillMenuRange() {
        int[] range = new int[actions.length];
        for (int i = 0; i < range.length; i++) {
            range[i] = actions[i].key();
        }
        return range;
    }


    public void run() {
        while (!exit) {
            this.showMenu();
            int numberOfAction = this.input.ask("Введите пункт меню : ", fillMenuRange());
            actions[numberOfAction].execute(input, tracker);
        }
    }

    private class CreateItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }

        @Override
        public String showInfo() {
            return "0. Add new Item";
        }
    }

    private static class ShowAllItems implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Полный список заявок --------------");
            Item[] items = tracker.findAll();
            for (Item curItem:items) {
                System.out.println(curItem);
            }
        }

        @Override
        public String showInfo() {
            return "1. Show all items";
        }
    }

    private class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
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

        @Override
        public String showInfo() {
            return "2. Edit item";
        }
    }

    private class FindItemById implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id заявки для поиска");
            Item foundItem = tracker.findById(id);
            if (foundItem != null) {
                System.out.println(foundItem);
            } else {
                System.out.println("Заявки с таким id не найдено");
            }
        }

        @Override
        public String showInfo() {
            return "4. Find item by id";
        }
    }

    private class FindItemsByName implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите наименование заявки");
            Item[] foundItems = tracker.findByName(name);
            if (foundItems.length == 0) {
                System.out.println("Заявок не найдено");
            } else {
                for (Item currItem : foundItems) {
                    System.out.println(currItem);
                }
            }
        }

        @Override
        public String showInfo() {
            return "5. Find items by name";
        }
    }

    private class ExitMenu implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            exit = true;
        }

        @Override
        public String showInfo() {
            return "6. Exit Program";
        }
    }

}

class DeleteItem implements UserAction {
    @Override
    public int key() {
        return 3;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
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

    @Override
    public String showInfo() {
        return "3. Delete item";
    }
}