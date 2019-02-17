package ru.job4j.io;

import java.io.*;
import java.util.Scanner;

public class CheckByteStream {
    boolean isNumber(InputStream in) throws IOException {
        return in.available() > 0 && in.read() % 2 == 0;
    }

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            for (String wrongString : abuse) {
                line = line.replaceAll(wrongString, "");
            }
            if (scanner.hasNext()) {
                line += System.lineSeparator();
            }
            out.write(line.getBytes());
        }
        out.flush();
    }
}
