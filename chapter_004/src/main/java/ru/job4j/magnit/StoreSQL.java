package ru.job4j.magnit;

import org.sqlite.jdbc4.JDBC4PreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StoreSQL implements AutoCloseable {
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ENTRY (id INTEGER PRIMARY KEY, eVALUE INTEGER)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS ENTRY";
    public static final String ADD_ROWS = "INSERT INTO ENTRY (eVALUE) VALUES (?)";
    public static final String SELECT_ROWS = "SELECT ID, eVALUE FROM ENTRY";
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config, Connection connection) {
        this.config = config;
        this.connect = connection;
    }

    public void generate(int size) {
        try (PreparedStatement createTable = this.connect.prepareStatement(
                CREATE_TABLE);
             PreparedStatement delTable = this.connect.prepareStatement(
                     DROP_TABLE)

        ) {
          this.connect.setAutoCommit(true);
          delTable.executeUpdate();
          createTable.executeUpdate();

          try (PreparedStatement createEntries = this.connect.prepareStatement(
                  ADD_ROWS)
          ) {
              this.connect.setAutoCommit(false);
              boolean isCommited = true;
              for (int i = 0; i < size; i++) {
                  createEntries.setInt(1, i);
                  createEntries.addBatch();
                  isCommited = false;
                  if ((i % 1000) == 0) {
                      createEntries.executeBatch();
                      connect.commit();
                      isCommited = true;
                  }
              }
              if (!isCommited) {
                  createEntries.executeBatch();
                  connect.commit();
              }
          }

        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException reallyBad) {
                reallyBad.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();
        try (PreparedStatement getEntries = this.connect.prepareStatement(SELECT_ROWS)) {
            if (getEntries.execute()) {
                ResultSet entries = getEntries.getResultSet();
                while (entries.next()) {
                    Entry entry = new Entry(entries.getInt("ID"), entries.getInt("eVALUE"));
                    result.add(entry);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}