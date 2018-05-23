package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream fakeConsole = new ByteArrayOutputStream();

    private void addMenuToStringBuilder(StringBuilder stringBuilder) {
        stringBuilder.append("****************" + System.lineSeparator() + "Меню." + System.lineSeparator() + "0. Add new Item" + System.lineSeparator() + "1. Show all items" + System.lineSeparator() + "2. Edit item" + System.lineSeparator());
        stringBuilder.append("3. Delete item" + System.lineSeparator() + "4. Find item by id" + System.lineSeparator() + "5. Find items by name" + System.lineSeparator() + "6. Exit Program" + System.lineSeparator());
    }

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.fakeConsole));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }


    @Test
    public void whenDeleteThenTrackerHasOneItemLess() {
        Tracker tracker = new Tracker();
        tracker.add(new Item());
        tracker.add(new Item());
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"3", item.getId(), "y", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().size(), is(2));
        assertThat(tracker.findById(item.getId()), is(Tracker.NULL_ITEM));
    }

    @Test
    public void whenWeHaveItemThenItCanBeFoundById() {
        Tracker tracker = new Tracker();
        tracker.add(new Item());
        tracker.add(new Item());
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void when2ItemsWithSameNameThenItFoundThemAll() {
        List<Item> result = new ArrayList<>();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("123", "321"));
        result.add(item);
        tracker.add(new Item("111", "343"));
        item = tracker.add(new Item("123", "321"));
        result.add(item);
        tracker.add(new Item("222", "333"));
        Input input = new StubInput(new String[]{"5", "123", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("123"), is(result));
    }

    @Test
    public void whenFindAllItemsThenAllItemsAreShown() {
        StringBuilder result = new StringBuilder();
        addMenuToStringBuilder(result);
        result.append("------------ Полный список заявок --------------" + System.lineSeparator());
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("123", "321"));
        result.append(item.toString() + System.lineSeparator());
        item = tracker.add(new Item("111", "343"));
        result.append(item.toString() + System.lineSeparator());
        item = tracker.add(new Item("123", "321"));
        result.append(item.toString() + System.lineSeparator());
        item = tracker.add(new Item("222", "333"));
        result.append(item.toString() + System.lineSeparator());
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        addMenuToStringBuilder(result);
        assertThat(fakeConsole.toString(), is(result.toString()));
    }

    @Test
    public void whenDeleteThenAskForConfirmation() {
        StringBuilder result = new StringBuilder();
        addMenuToStringBuilder(result);
        Tracker tracker = new Tracker();
        tracker.add(new Item());
        tracker.add(new Item());
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"3", item.getId(), "y", "6"});
        new StartUI(input, tracker).init();
        addMenuToStringBuilder(result);
        assertThat(fakeConsole.toString(), is(result.toString()));

    }

}
