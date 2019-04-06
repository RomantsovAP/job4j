package ru.job4j.io.filesearch;

public enum SearchOption {
    MASK("-m"), REGEXP("-r"), FILENAME("-f"), NONE("");

    public final String key;

    SearchOption(String key) {
        this.key = key;
    }
}
