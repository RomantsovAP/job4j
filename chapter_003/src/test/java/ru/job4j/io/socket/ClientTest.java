package ru.job4j.io.socket;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    private static final String LN = System.lineSeparator();


    @Test
    public void whenSendGoodByeItFinishes() throws IOException {
        String ask = "пока" + LN;
        String answ = "";

        InputStream bckIn = System.in;
        PrintStream bckOut = System.out;
        ByteArrayInputStream inStream = new ByteArrayInputStream(ask.getBytes());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket);
        client.start();
        assertThat(outStream.toString(), is(answ));
        assertThat(out.toString(), is(ask));
        System.setIn(bckIn);
        System.setOut(bckOut);

    }

    @Test
    public void whenSendSmthItSendIt() throws IOException {
        String ask = "...fff" + LN + "пока" + LN;
        String servAnsw = "..." + LN + LN;

        InputStream bckIn = System.in;
        PrintStream bckOut = System.out;
        ByteArrayInputStream inStream = new ByteArrayInputStream(ask.getBytes());
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(byteOut);
        System.setIn(inStream);
        System.setOut(outStream);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(servAnsw.getBytes());
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        Client client = new Client(socket);
        client.start();
        assertThat(byteOut.toString(), is(servAnsw));
        assertThat(out.toString(), is(ask));
        System.setIn(bckIn);
        System.setOut(bckOut);

    }
}
