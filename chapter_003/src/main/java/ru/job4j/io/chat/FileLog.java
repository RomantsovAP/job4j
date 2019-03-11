package ru.job4j.io.chat;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FileLog implements Log {
    private FileWriter writer;

    public FileLog(Path logPath) throws IOException {
        writer = new FileWriter(logPath.toFile());
    }

    @Override
    public void log(String message) {
        try {
            writer.append(message + System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
