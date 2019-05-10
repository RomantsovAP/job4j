package ru.job4j.tracker;

interface UserAction {

    int key();

    void execute(Input input, ITracker tracker);

    String showInfo();

}
