package ru.job4j.io.chat;

public interface Bot {
    String reply();
    void release();
    void toggle();
    boolean isActive();
}
