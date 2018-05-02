package ru.job4j.tracker;

import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
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
        assertThat(tracker.findAll().length, is(2));
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
        Item[] result = new Item[2];
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("123", "321"));
        result[0] = item;
        tracker.add(new Item("111", "343"));
        item = tracker.add(new Item("123", "321"));
        result[1] = item;
        tracker.add(new Item("222", "333"));
        Input input = new StubInput(new String[]{"5", "123", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("123"), is(result));
    }

}
