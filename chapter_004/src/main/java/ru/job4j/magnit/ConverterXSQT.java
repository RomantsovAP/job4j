package ru.job4j.magnit;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class ConverterXSQT {
    private static final String LINE_SEPARATOR = "\n";

    public void convert(File source, File dest, String schema) {

        String xml = "";
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(source)) {
            char[] buff = new char[1024];
            int l = 0;
            while ((l = fileReader.read(buff)) > 0) {
                sb.append(buff, 0, l);
            }
            xml = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(dest)) {
            TransformerFactory factory = TransformerFactory.newInstance();
            try {
                Transformer transformer = factory.newTransformer(new StreamSource(new ByteArrayInputStream(schema.getBytes())));
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.setOutputProperty("{http://xml.apache.org/xalan}line-separator", LINE_SEPARATOR);
                transformer.setOutputProperty("{http://xml.apache.org/xslt}line-separator", LINE_SEPARATOR);
                transformer.transform(new StreamSource(new ByteArrayInputStream(xml.getBytes())), new StreamResult(fileOutputStream));
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
