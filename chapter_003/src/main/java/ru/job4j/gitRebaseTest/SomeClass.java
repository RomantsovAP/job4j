package ru.job4j.gitRebaseTest;

public class SomeClass {

    private String name;
    private int value;

    public SomeClass(String name) {
        this.name = name;
        this.value = 0;
    }

    public SomeClass(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
