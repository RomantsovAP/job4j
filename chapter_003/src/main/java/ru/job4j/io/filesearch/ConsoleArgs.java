package ru.job4j.io.filesearch;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ConsoleArgs implements Args {

    private static final String[] VALID_KEYS = {"-d", "-n", "-m", "-f", "-r", "-o"};
    private static final String[] SEARCH_KEYS = {"-m", "-f", "-r"};
    private boolean printHelp;
    private SearchOption searchOption;
    private String searchValue;
    private Path dir;
    private Path logPath;
    private boolean writeOutputToFile = false;

    public ConsoleArgs(String[] args) {
        List<String> argList = Arrays.asList(args);
        printHelp = argList.contains("-help");
        searchOption = SearchOption.NONE;
        checkMultiParamKeys(argList);

        Arrays.stream(SearchOption.values()).filter(it -> !it.equals(SearchOption.NONE)).forEach(it -> {
            if (argList.contains(it.key)) {
                searchOption = it;
                searchValue = getArgValue(argList, "-n");
            }
        });

        if (argList.contains("-d") && argList.indexOf("-d") != argList.size() - 1) {
            dir = Paths.get(argList.get(argList.indexOf("-d") + 1));
        } else {
            dir = Paths.get(System.getProperty("user.dir"));
        }

        if (argList.contains("-o") && argList.indexOf("-o") != argList.size() - 1) {
            logPath = Paths.get(argList.get(argList.indexOf("-o") + 1));
            writeOutputToFile = true;
        }
    }

    private void checkMultiParamKeys(List<String> argList) throws IllegalArgumentException {
        int searchKeysCount = 0;
        List<String> searchKeysList = Arrays.asList(SEARCH_KEYS);
        for (String key: VALID_KEYS) {
            int freq = Collections.frequency(argList, key);
            if (freq > 1) {
                throw new IllegalArgumentException("To many " + key + " in args");
            }
            if (freq != 0 && searchKeysList.contains(key)) {
                searchKeysCount++;
            }
        }
        if (searchKeysCount > 1) {
            throw new IllegalArgumentException("To many search options");
        }
    }

    private String getArgValue(List<String> argList, String key) throws IllegalArgumentException {
        if (!argList.contains(key)) {
            throw new IllegalArgumentException("No -n key while search option is present");
        }
        if (argList.indexOf(key) == argList.size() - 1) {
            throw new IllegalArgumentException("No value for -n key");
        }
        return argList.get(argList.indexOf(key) + 1);
    }



    @Override
    public Path getDirectory() {
        return dir;
    }

    @Override
    public String getSearchValue() {
        return searchValue;
    }

    @Override
    public SearchOption getSearchOption() {
        return searchOption;
    }

    @Override
    public boolean writeOutputToFile() {
        return writeOutputToFile;
    }

    @Override
    public Path getLogPath() {
        return logPath;
    }

    @Override
    public boolean printHelp() {
        return printHelp;
    }
}
