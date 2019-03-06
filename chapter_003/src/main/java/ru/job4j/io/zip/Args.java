package ru.job4j.io.zip;

import java.nio.file.Path;

public interface Args {
    Path directory();
    String exclude();
    Path output();
}
