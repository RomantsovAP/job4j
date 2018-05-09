package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {

    @Override
    public String ask(String question) {
        System.out.println(question);
        return new Scanner(System.in).nextLine();
    }

    @Override
    public int ask(String question, int[] range) throws MenuOutException, NumberFormatException {
        int answ;
        boolean isNormAnsw = false;
        System.out.println(question);
        answ = Integer.parseInt(new Scanner(System.in).nextLine());
        for (int i = 0; i < range.length; i++) {
            if (range[i] == answ) {
                isNormAnsw = true;
                break;
            }
        }
        if (!isNormAnsw) {
            throw new MenuOutException("Menu out exception ");
        }
        return answ;
    }
}
