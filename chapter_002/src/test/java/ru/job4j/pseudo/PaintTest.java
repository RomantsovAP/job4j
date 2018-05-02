package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {

    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream fakeConsole = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.fakeConsole));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }


    @Test
    public void whenTriangleDrawThenItOutputTriangleToConsole() {
        String result = new StringBuilder()
                .append("*")
                .append(System.lineSeparator())
                .append("**")
                .append(System.lineSeparator())
                .append("***")
                .append(System.lineSeparator())
                .append("****")
                .append(System.lineSeparator())
                .toString();

        Paint paint = new Paint();
        paint.draw(new Triangle());
        assertThat(result, is(this.fakeConsole.toString()));
    }

    @Test
    public void whenSquareDrawThenItOutputSquareToConsole() {
        String result = new StringBuilder()
                .append("****")
                .append(System.lineSeparator())
                .append("****")
                .append(System.lineSeparator())
                .append("****")
                .append(System.lineSeparator())
                .append("****")
                .append(System.lineSeparator())
                .toString();

        Paint paint = new Paint();
        paint.draw(new Square());
        assertThat(result, is(this.fakeConsole.toString()));
    }
}
