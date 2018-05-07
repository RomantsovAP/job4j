package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) {
        int answ = -1;
        boolean weHaveValidAnswer = false;
        while (!weHaveValidAnswer) {
            try {
                answ = super.ask(question, range);
                weHaveValidAnswer = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid data!");
            } catch (MenuOutException e) {
                System.out.println("Please enter valid point menu number!");
            }
        }
        return answ;
    }
}
