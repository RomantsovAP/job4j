package ru.job4j.io.chat;

public interface Log extends AutoCloseable {
    void log(String message);
    void close();
}
