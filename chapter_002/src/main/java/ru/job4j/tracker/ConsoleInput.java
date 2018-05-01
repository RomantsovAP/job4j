package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {

    @Override
    public String ask(String question) {
        System.out.println(question);
        return new Scanner(System.in).nextLine();
    }
}
