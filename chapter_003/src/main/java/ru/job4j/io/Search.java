package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 17.02.2019
 */

/**
 * Class for searching files in directory
 */
public class Search {
    /**
     * @param parent - parent folder
     * @param exts - files extensions
     * @return - files, if found some
     */
    List<File> files(String parent, List<String> exts) {
        List<File> fileList = new ArrayList<>();
        Deque<File> directories = new LinkedList<>();
        directories.push(new File(parent));
        while (!directories.isEmpty()) {
            File currDir = directories.pollLast();
            for (File currFile : currDir.listFiles()) {
                if (currFile.isDirectory()) {
                    directories.push(currFile);
                } else {
                    String[] nameParts = currFile.getName().split("\\.");
                    if (nameParts.length > 1) {
                        String extension = nameParts[nameParts.length - 1];
                        if (exts.contains(extension)) {
                            fileList.add(currFile);
                        }
                    }
                }
            }
        }
        return fileList;
    }

}
