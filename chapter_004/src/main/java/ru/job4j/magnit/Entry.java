package ru.job4j.magnit;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "entry")
public class Entry {

    @XmlElement
    private int id;
    @XmlElement
    private int value;

    public Entry(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public Entry() {
    }
}
