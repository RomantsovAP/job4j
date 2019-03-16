package ru.job4j.io.zip;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * console archiver
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 06.03.2019
 */

public class Pack {

    private final Args args;
    private final static Logger LOGGER = Logger.getLogger(Pack.class);

    private Pack(Args args) {
         this.args = args;
    }

    /**
     * @param args - string -d C:\tt\zzz\ -e xml -o project.zip
     * -d - what to pack
     * -e - what to exclude
     * -o - what place to put result
     */
    public static void main(String[] args) {
        try {
            Pack archiver = new Pack(new ConsoleArgs(args));
            archiver.createZip(archiver.args.directory());
        } catch (IllegalArgumentException e) {
            LOGGER.error("try with correct arguments", e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void createZip(Path source) throws IOException {
        Path zipFile = args.output();
        Path zipDirectory = zipFile.getParent();
        if (Files.notExists(zipDirectory)) {
            Files.createDirectories(zipDirectory);
        }
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {
            if (Files.isDirectory(source)) {
                List<Path> fileNames = new ArrayList<>();
                collectFileList(source, fileNames);
                for (Path fileName : fileNames) {
                    addNewZipEntry(zipOutputStream, source, fileName);
                }
            } else {
                throw new IllegalArgumentException("wrong source" + source);
            }
        }
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws IOException {
        Path fullPath = filePath.resolve(fileName);
        try (InputStream inputStream = Files.newInputStream(fullPath)) {
            ZipEntry entry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(entry);
            copyData(inputStream, zipOutputStream);
            zipOutputStream.closeEntry();
        }
    }

    private void collectFileList(Path path, List<Path> fileList) throws IOException {
        if (Files.isRegularFile(path) && !path.equals(args.output()) && !isExtensionExcluded(path)) {
            Path relativePath = args.directory().relativize(path);
            fileList.add(relativePath);
        }
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                for (Path file : directoryStream) {
                    collectFileList(file, fileList);
                }
            }
        }
    }

    private boolean isExtensionExcluded(Path path) {
        String fileName = path.toString();
        int lastDotPosition = fileName.lastIndexOf('.');
        String ext = fileName.substring(lastDotPosition + 1, fileName.length() - 1);
        return args.exclude().contains(ext);
    }

    private void copyData(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
