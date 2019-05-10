package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {



    @Test
    public void checkConnection() {
        try (TrackerSQL sql = new TrackerSQL()) {
            assertThat(sql.init(), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddItemItWorks() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            sql.delete("1");
            Item addItem = new Item("1", "test1", "desk1");
            sql.add(addItem);
            assertThat(sql.findAll().contains(addItem), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenReplaceItemItWorks() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            sql.delete("1");
            Item addItem = new Item("1", "test1", "desk1");
            sql.add(addItem);
            Item newItem = new Item("1", "test22222", "desk1");
            sql.replace("1", newItem);
            assertThat(sql.findById("1").getName(), is("test22222"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindByNameItemItWorks() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            sql.delete("1");
            Item addItem = new Item("1", "test1", "desk1");
            sql.add(addItem);
            assertThat(sql.findByName("test1").contains(addItem), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
