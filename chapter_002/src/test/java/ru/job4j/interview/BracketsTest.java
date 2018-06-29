package ru.job4j.interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



public class BracketsTest {
    @Test
    public void thenWrongExpressionWhenItThrowException() {
        Boolean answ = true;
        Brackets brackets = new Brackets();
        try {
            brackets.parseBrackets("ddd[aa{dd(dd}ddd]dd)ddsdf");
        } catch (Exception e) {
            answ = false;
        }
        assertThat(answ, is(false));
    }


    @Test
    public void thenRightExpressionWhenItDontThrowException() {
        Boolean answ = true;
        Brackets brackets = new Brackets();
        try {
            brackets.parseBrackets("dd[ddd{sss(dd[ddd]dd{ddd}ddd(d{dd}[][][dd]dd)dd)}d]d");
        } catch (Exception e) {
            answ = false;
        }
        assertThat(answ, is(true));
    }

    @Test
    public void thenRightExpressionWhenItReturnRightListOfBrackets() {
        //(a[b{c}de]xxx)
        //0123456789
        ArrayList<Brackets.Bracket> answ = new ArrayList<>();
        answ.add(new Brackets.Bracket(4, '{'));
        answ.add(new Brackets.Bracket(6, '}'));
        answ.add(new Brackets.Bracket(2, '['));
        answ.add(new Brackets.Bracket(9, ']'));
        answ.add(new Brackets.Bracket(0, '('));
        answ.add(new Brackets.Bracket(13, ')'));
        Brackets brackets = new Brackets();
        System.out.println(answ);
        assertThat(answ, is(brackets.parseBrackets("(a[b{c}de]xxx)")));
    }

    @Test
    public void thenBigExpressionWhenItReturnREallyBigListOfBrackets() {
        //{[()()()][()()]}
        ArrayList<Brackets.Bracket> answ = new ArrayList<>();
        answ.add(new Brackets.Bracket(2, '('));
        answ.add(new Brackets.Bracket(3, ')'));
        answ.add(new Brackets.Bracket(4, '('));
        answ.add(new Brackets.Bracket(5, ')'));
        answ.add(new Brackets.Bracket(6, '('));
        answ.add(new Brackets.Bracket(7, ')'));
        answ.add(new Brackets.Bracket(1, '['));
        answ.add(new Brackets.Bracket(8, ']'));
        answ.add(new Brackets.Bracket(10, '('));
        answ.add(new Brackets.Bracket(11, ')'));
        answ.add(new Brackets.Bracket(12, '('));
        answ.add(new Brackets.Bracket(13, ')'));
        answ.add(new Brackets.Bracket(9, '['));
        answ.add(new Brackets.Bracket(14, ']'));
        answ.add(new Brackets.Bracket(0, '{'));
        answ.add(new Brackets.Bracket(15, '}'));

        Brackets brackets = new Brackets();
        assertThat(answ, is(brackets.parseBrackets("{[()()()][()()]}")));
    }

}
