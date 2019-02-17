package ru.job4j.io;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class CheckByteStreamTest {

    private String data;

    @Before
    public void init() {
        data = "Однажды в студеную зимнюю пору" + System.lineSeparator() + "я из лесу вышел, был сильный мороз"
                + System.lineSeparator() + "смотрю поднимается медленно в гору" + System.lineSeparator()
                + "лошадка везущая хворосту воз";
    }

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

    @Test
    public void whenNoAbuseInStreamThenInEqualsOut() {
        CheckByteStream checkByteStream = new CheckByteStream();
        try (InputStream in = new ByteArrayInputStream(data.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            checkByteStream.dropAbuses(in, out, new String[0]);
            Assert.assertArrayEquals(out.toByteArray(), data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSomeAbuseInStreamThenInEqualsOutWithoutAbuse() {
        CheckByteStream checkByteStream = new CheckByteStream();
        try (InputStream in = new ByteArrayInputStream(data.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            checkByteStream.dropAbuses(in, out, new String[]{"зимнюю", "хворосту"});
            Assert.assertArrayEquals(out.toByteArray(),
                    data.replaceAll("зимнюю", "").replaceAll("хворосту", "").getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
