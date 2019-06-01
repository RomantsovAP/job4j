package ru.job4j.magnit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Config config = new Config();
        config.init();
        config.createNewDatabase();
        try (Connection connect = DriverManager.getConnection(config.get("url"))) {
            StoreSQL storeSQL = new StoreSQL(config, connect);
            storeSQL.generate(1000000);
            List<Entry> list = storeSQL.load();
            StoreXML storeXML = new StoreXML(new File(config.get("xmlfilename")));
            storeXML.save(list);
            ConverterXSQT converterXSQT = new ConverterXSQT();
            converterXSQT.convert(new File(config.get("xmlfilename")), new File(config.get("xmlTransfilename")), config.getSchema());
            XmlParser xmlParser = new XmlParser();
            xmlParser.parse(new File(config.get("xmlTransfilename")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
