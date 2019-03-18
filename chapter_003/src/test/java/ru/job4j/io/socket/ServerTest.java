package ru.job4j.io.socket;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private static final String LN = System.lineSeparator();

    private void testServer(String ask, String answ) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(ask.getBytes());
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(answ));
    }

    @Test
    public void whenSendGoodByeItFinishes() throws IOException {
        String ask = "пока";
        String answ = "";
        testServer(ask, answ);
    }

    @Test
    public void whenSendHelloItReplyCorrect() throws IOException {
        String ask = "Hello oracle" + LN + "пока" + LN;
        String answ = "Hello, dear friend, I'm a oracle." + LN + "Second line..." + LN + LN;
        testServer(ask, answ);
    }

    @Test
    public void whenSendSmthItReplySmt() throws IOException {
        String ask = "Hfffle" + LN + "пока" + LN;
        String answ = "..." + LN + LN;
        testServer(ask, answ);
    }
}
