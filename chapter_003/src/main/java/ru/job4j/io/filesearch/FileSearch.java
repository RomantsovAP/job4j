package ru.job4j.io.filesearch;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/*
1. Создать программу для поиска файла.
2. Программа должна искать данные в заданном каталоге и подкаталогах.
3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
Ключи
-d - директория в которая начинать поиск.
-n - имя файл, маска, либо регулярное выражение.
-m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
-o - результат записать в файл.
5. Программа должна записывать результат в файл.
6. В программе должна быть валидация ключей и подсказка -help.
 */

public class FileSearch {
    public static void main(String[] args) throws IOException {
        FileSearch fileSearch = new FileSearch();
        Args consoleArgs = new ConsoleArgs(args);
        FileWriter log = null;
        if (consoleArgs.writeOutputToFile()) {
            log = new FileWriter(consoleArgs.getLogPath().toFile());
        }
        try {
            fileSearch.search(consoleArgs, log);
        } finally {
            if (consoleArgs.writeOutputToFile()) {
                log.close();
            }
        }
    }

    public void search(Args args, FileWriter log) throws IOException {

        Files.walkFileTree(args.getDirectory(), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (checkSearchOption(args.getSearchOption(), file)) {
                    if (!args.writeOutputToFile()) {
                        System.out.println(file.getFileName());
                    } else {
                        log.write(file.toString() + System.lineSeparator());
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            private boolean checkSearchOption(SearchOption option, Path file) {
                boolean result;
                switch (args.getSearchOption()) {
                    case MASK:
                        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + args.getSearchValue());
                        result = pathMatcher.matches(file.getFileName());
                        break;
                    case REGEXP:
                        result = file.getFileName().toString().matches(args.getSearchValue());
                        break;
                    case FILENAME:
                        result = file.getFileName().toString().equals(args.getSearchValue());
                        break;
                    default:
                        result = true;
                }
                return result;
            }
        });
    }

}
