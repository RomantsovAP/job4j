package ru.job4j.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {
    @Test
    public void whenTriangleDrawThenItOutputTriangleToConsole() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream fakeConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(fakeConsole));
        String result = "*" + System.lineSeparator()
                        + "**" + System.lineSeparator()
                        + "***" + System.lineSeparator()
                        + "****"  + System.lineSeparator();

        Paint paint = new Paint();
        paint.draw(new Triangle());
        assertThat(result, is(fakeConsole.toString()));
        System.setOut(stdout);
    }

    @Test
    public void whenSquareDrawThenItOutputSquareToConsole() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream fakeConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(fakeConsole));
        String result = "****" + System.lineSeparator()
                + "****" + System.lineSeparator()
                + "****" + System.lineSeparator()
                + "****"  + System.lineSeparator();

        Paint paint = new Paint();
        paint.draw(new Square());
        assertThat(result, is(fakeConsole.toString()));
        System.setOut(stdout);
    }
}
