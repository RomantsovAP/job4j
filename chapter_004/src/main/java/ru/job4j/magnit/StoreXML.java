package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.List;
import java.util.Map;

public class StoreXML {
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) {
        try (StringWriter writer = new StringWriter();
             FileWriter fw = new FileWriter(this.target)) {
            convertToXml(writer, list);
            fw.write(writer.toString());
            fw.flush();

        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

    }

    private void convertToXml(StringWriter writer, List<Entry> list) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Entries.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(new Entries(list), writer);
    }
}
