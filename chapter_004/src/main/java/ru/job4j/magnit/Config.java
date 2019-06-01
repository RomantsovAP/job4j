package ru.job4j.magnit;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Config {
    private final Properties values = new Properties();
    private String schema;

    public String getSchema() {
        return schema;
    }

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app2.properties");
             BufferedReader schemaIn = new BufferedReader(
                     new InputStreamReader(Config.class.getClassLoader().getResourceAsStream("schema.xslt")));
             StringWriter sw = new StringWriter()) {
            values.load(in);

            String line;
            while ((line = schemaIn.readLine()) != null) {
                sw.write(line);
            }
            sw.flush();
            this.schema = sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }

    public void createNewDatabase() {

        try (Connection conn = DriverManager.getConnection(get("url"))) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

