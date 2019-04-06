package ru.job4j.io.filesearch;

import java.nio.file.Path;

public interface Args {
    Path getDirectory();
    SearchOption getSearchOption();
    String getSearchValue();
    boolean writeOutputToFile();
    Path getLogPath();
    boolean printHelp();
}
