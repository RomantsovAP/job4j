package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CheckByteStreamTest {

    @Test
    public void whenEvenNumberInStreamThenTrue() {
        CheckByteStream checkByteStream = new CheckByteStream();
        try (InputStream in = new ByteArrayInputStream(new byte[]{6})) {
            Assert.assertTrue(checkByteStream.isNumber(in));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenOdNumberInStreamThenFalse() {
        CheckByteStream checkByteStream = new CheckByteStream();
        try (InputStream in = new ByteArrayInputStream(new byte[]{5})) {
            Assert.assertFalse(checkByteStream.isNumber(in));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenEmptyStreamThenFalse() {
        CheckByteStream checkByteStream = new CheckByteStream();
        try (InputStream in = new ByteArrayInputStream(new byte[0])) {
            Assert.assertFalse(checkByteStream.isNumber(in));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
