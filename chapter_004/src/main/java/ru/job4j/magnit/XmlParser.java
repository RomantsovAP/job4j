package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XmlParser {

    public void parse(File file) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ParseHandler handler = new ParseHandler();
            saxParser.parse(file, handler);

            System.out.println(handler.sum);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private class ParseHandler extends DefaultHandler {

        private int sum = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {

            if (qName.equalsIgnoreCase("Entry")) {
                Integer curValue = Integer.parseInt(attributes.getValue("value"));
                sum += curValue;
            }
        }

    }
}

