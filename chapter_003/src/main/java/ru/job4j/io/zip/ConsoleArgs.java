package ru.job4j.io.zip;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * args for archiver
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 06.03.2019
 */
public class ConsoleArgs implements Args {

    private Path directory;
    private String exclude;
    private Path output;

    ConsoleArgs(String[] args) {
        // java -jar pack.jar -d c:\project\job4j\ -e xml -o project.zip
        String[] args2 = args.clone();
        if ((args2.length % 2) != 0 || args2.length < 4) {
            throw new IllegalArgumentException("Bad arguments");
        }
        for (int i = 0; i < args2.length / 2; i++) {
            int posArg = 2 * i + 1;
            int posKey = 2 * i;
            switch (args2[posKey]) {
                case "-d" : directory   = Paths.get(args2[posArg]); break;
                case "-e" : exclude     = args2[posArg];            break;
                case "-o" : output      = Paths.get(directory.toString() + '\\' + args[posArg]);   break;
                default: throw new IllegalArgumentException("Bad key " + args2[posKey]);
            }
        }
    }


    public Path directory() {
        return directory;
    }

    public String exclude() {
        return exclude;
    }

    public Path output() {
        return output;
    }

}
