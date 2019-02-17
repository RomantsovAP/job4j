package ru.job4j.io;

import java.io.IOException;
import java.io.InputStream;

public class CheckByteStream {
    boolean isNumber(InputStream in) throws IOException {
        return in.available() > 0 && in.read() % 2 == 0;
    }
}
