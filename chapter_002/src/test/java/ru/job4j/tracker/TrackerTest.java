package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", "123L");
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }


    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();

        Item previous = new Item("test1", "testDesc1");
        tracker.add(previous);

        Item next = new Item("test2", "testDesc2");

        tracker.replace(previous.getId(), next);
        next.setId(previous.getId());

        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenAddItemThenWeHaveMoreItemsInTracker() {
        Tracker tracker = new Tracker();
        Item curItem;
        List<Item> resultQue = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            curItem = new Item("item" + i, "desk" + i);
            tracker.add(curItem);
            resultQue.add(curItem);
        }

        assertThat(tracker.findAll(), is(resultQue));
    }

    @Test public void whenDeleteSomeItemThenItDeletes() {
        Tracker tracker = new Tracker();
        Item curItem;
        List<Item> resultQue = new ArrayList<>();

        curItem = new Item("item1", "desk1");
        tracker.add(curItem);
        resultQue.add(curItem);

        curItem = new Item("item2", "desk2");
        tracker.add(curItem);
        String id = curItem.getId();

        curItem = new Item("item3", "desk3");
        tracker.add(curItem);
        resultQue.add(curItem);

        curItem = new Item("item4", "desk4");
        tracker.add(curItem);
        resultQue.add(curItem);

        tracker.delete(id);
        assertThat(tracker.findAll(), is(resultQue));
    }

    @Test public void whenFindByNameLastElemThenItFindIt() {
        Tracker tracker = new Tracker();
        Item curItem;
        List<Item> result = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            curItem = new Item("item" + i, "desk" + i);
            tracker.add(curItem);
            if (i == 3) {
                 result.add(curItem);
            }
        }

        assertThat(tracker.findByName("item3"), is(result));
    }

    @Test public void whenFindByNameAllElemsThenItFindItAll() {
        Tracker tracker = new Tracker();
        Item curItem;
        List<Item> result = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            curItem = new Item("item1", "desk1");
            tracker.add(curItem);
            result.add(curItem);
        }

        assertThat(tracker.findByName("item1"), is(result));
    }


}
