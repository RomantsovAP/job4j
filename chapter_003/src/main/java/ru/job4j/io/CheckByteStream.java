package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    void dropAbuses_mk2(InputStream in, OutputStream out, final String[] abuse) throws IOException {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            line = Arrays.stream(line.split(" ")).filter(s -> !Arrays.stream(abuse).anyMatch(s1 -> s1.equals(s))).collect(Collectors.joining(" "));

            if (scanner.hasNext()) {
                line += System.lineSeparator();
            }
            out.write(line.getBytes());
        }
        out.flush();
    }

    void dropAbuses_mk3(final InputStream in, final OutputStream out, final String[] abuse) throws IOException {
        try (final PrintStream writer = new PrintStream(out);
             final BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            reader.lines()
                    .map(s -> Arrays.stream(abuse)
                            .reduce(s, (s1, s2) -> s1.replaceAll(s2, ""))
                    ).forEach(writer::println);
        }
    }
}
