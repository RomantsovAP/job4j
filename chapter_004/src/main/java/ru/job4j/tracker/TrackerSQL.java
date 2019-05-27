package ru.job4j.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class TrackerSQL implements ITracker, AutoCloseable {

    private static final String ADD_ITEM_SQL_SRIPT = "INSERT INTO items (id, name, desk) VALUES (?,?,?)";
    private static final String REPLACE_ITEM_SQL_SCRIPT = "UPDATE items SET name = ?, desk = ? WHERE id = ?";
    private static final String DELETE_ITEM_SQL_SCRIPT = "DELETE  from items WHERE id = ?";
    private static final String FIND_ALL_SQL_SCRIPT = "SELECT  * from items";
    private static final String FIND_BY_NAME_SQL_SCRIPT = "SELECT  * from items WHERE name = ?";
    private static final String FIND_BY_ID_SQL_SCRIPT = "SELECT  * from items WHERE id = ?";

    private Connection connection;
    private static final Item EMPTY_ITEM = new Item("empty", "empty", "empty");

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            initTables();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    private void executeSqlScript(String sqlScript) throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(TrackerSQL.class.getClassLoader().getResourceAsStream(sqlScript)))
            ) {
            String script = reader.lines().collect(Collectors.joining("\n"));
            try (Statement initdb = connection.createStatement()) {
                initdb.execute(script);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTables() throws IOException, SQLException {
        executeSqlScript("initTables.sql");
    }

    public static void main(String[] args) {
        try (TrackerSQL trackerSQL = new TrackerSQL()) {
            trackerSQL.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement query = connection.prepareStatement(ADD_ITEM_SQL_SRIPT)) {
            query.setInt(1, Integer.valueOf(item.getId()));
            query.setString(2, item.getName());
            query.setString(3, item.getDesk());
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void replace(String id, Item item) {
        try (PreparedStatement query = connection.prepareStatement(REPLACE_ITEM_SQL_SCRIPT)) {
            query.setString(1, item.getName());
            query.setString(2, item.getDesk());
            query.setInt(3, Integer.valueOf(id));
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE_ITEM_SQL_SCRIPT)) {
            query.setInt(1, Integer.valueOf(id));
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL_SQL_SCRIPT)) {
            query.execute();
            ResultSet result = query.getResultSet();
            while (result.next()) {
                items.add(new Item(
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("desk")
                    ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_NAME_SQL_SCRIPT)) {
            query.setString(1, key);
            query.execute();
            ResultSet result = query.getResultSet();
            while (result.next()) {
                items.add(new Item(
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("desk")
                    ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item item = EMPTY_ITEM;
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_ID_SQL_SCRIPT)) {
            query.setInt(1, Integer.valueOf(id));
            query.execute();
            ResultSet result = query.getResultSet();
            if (result.next()) {
                item = new Item(
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("desk")
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
