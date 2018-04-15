
package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test-class for converter
 */
public class ConverterTest {
    /**
     * 60 rub = 1 $ test
     */
    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(60);
        assertThat(result, is(1));
    }

    /**
     * 70 rub = 1 eur test
     */
    @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(70);
        assertThat(result, is(1));
    }

    /**
     * 5$ = 300 RUB test
     */
    @Test
    public void when5DollarsToRubThen300() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(5);
        assertThat(result, is(300));
    }

    /**
     * 4 EUR = 350 RUB test
     */
    @Test
    public void when5EuroToRubThen350() {
        Converter converter = new Converter();
        int result = converter.euroToRubble(5);
        assertThat(result, is(350));
    }
}
