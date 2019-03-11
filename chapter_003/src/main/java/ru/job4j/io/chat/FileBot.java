package ru.job4j.io.chat;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;

public class FileBot implements Bot {


    private ArrayList<String> phrases;
    private Random rnd = new Random();
    private boolean isClosed = false;
    private boolean isActive;

    FileBot(File file) throws IOException {
        phrases = new ArrayList<>(Files.readAllLines(file.toPath()));
        this.isActive = true;
    }

    @Override
    public String reply() {
        String answer;
        if (isClosed) {
            throw new RuntimeException("bot was released!");
        }
        if (isActive) {
            answer = phrases.get(rnd.nextInt(phrases.size()));
        } else {
            answer = "I was turned off";
        }
        return answer;
    }

    public void release() {
        isClosed = true;
    }

    @Override
    public void toggle() {
        isActive = !isActive;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }
}
