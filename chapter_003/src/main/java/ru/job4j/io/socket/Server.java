package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Server implements AutoCloseable {
    private static final String HELLO = "Hello oracle";
    private static final String GOOD_BYE = "пока";
    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String ask;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if (HELLO.equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println("Second line...");
                    out.println();
                } else if (!GOOD_BYE.equals(ask)) {
                    out.println("...");
                    out.println();
                }
            } while (!GOOD_BYE.equals(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("You should specify 1 parameter - port number");
        }
        try (Server server = new Server(new ServerSocket(Integer.parseInt(args[0])).accept())) {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}
