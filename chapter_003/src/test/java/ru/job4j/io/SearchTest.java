package ru.job4j.io;

import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchTest {

    private final String testDir = System.getProperty("java.io.tmpdir") + java.util.UUID.randomUUID() + "\\";
    private final LinkedList<Path> pathsOfTmpFiles = new LinkedList<>();

    @Before
    public void init() throws IOException {
        pathsOfTmpFiles.addFirst(Files.createDirectory(Paths.get(testDir)));
        pathsOfTmpFiles.addFirst(Files.createDirectories(Paths.get(testDir + "1\\")));
        pathsOfTmpFiles.addFirst(Files.createDirectories(Paths.get(testDir + "1\\12\\")));
        pathsOfTmpFiles.addFirst(Files.createDirectories(Paths.get(testDir + "2\\")));
        
        pathsOfTmpFiles.addFirst(Files.createFile(Paths.get(testDir + "1\\1.txt"))); //5
        pathsOfTmpFiles.addFirst(Files.createFile(Paths.get(testDir + "1\\12\\2.txt"))); // 4
        pathsOfTmpFiles.addFirst(Files.createFile(Paths.get(testDir + "1\\12\\1.php"))); //3
        pathsOfTmpFiles.addFirst(Files.createFile(Paths.get(testDir + "2\\3.txt"))); //2
        pathsOfTmpFiles.addFirst(Files.createFile(Paths.get(testDir + "2\\4.txt"))); //1
        pathsOfTmpFiles.addFirst(Files.createFile(Paths.get(testDir + "2\\2.php"))); //0
    }

    @Ignore
    @Test
    public void whenSeekForSomeFilesItFindThem() {
        Search search = new Search();
        List<String> exts = new ArrayList<>();
        exts.add("txt");
        List<File> fileList = search.files(testDir, exts);
        List<File> expected = new ArrayList<>();
        expected.add(pathsOfTmpFiles.get(5).toFile());
        expected.add(pathsOfTmpFiles.get(2).toFile());
        expected.add(pathsOfTmpFiles.get(1).toFile());
        expected.add(pathsOfTmpFiles.get(4).toFile());
        Assert.assertArrayEquals(expected.toArray(), fileList.toArray());
    }

    @After
    public void clearTemp() throws IOException {
        for (Path file: pathsOfTmpFiles) {
            Files.delete(file);
        }
    }
}
