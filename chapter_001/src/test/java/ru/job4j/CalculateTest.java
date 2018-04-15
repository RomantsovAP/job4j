package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
@author Romantsov Aleksey
*/
public class CalculateTest {
	/**
	* Test echo.
	*/ @Test
	public void whenTakeNameThenTreeEchoPlusName() {
		String input = "Aleksey Romantsov";
		String expect = "Echo, echo, echo : Aleksey Romantsov";
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}

}