package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client implements AutoCloseable {
    private static final String GOOD_BYE = "пока";
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            do {
                input = console.readLine();
                out.println(input);
                if (!GOOD_BYE.equals(input)) {
                    String str;
                    do {
                        str = in.readLine();
                        System.out.println(str);
                    } while (!str.isEmpty());
                }

            } while (!GOOD_BYE.equals(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("You should specify 2 parameter - host, and port number");
        }
        try (Client client = new Client(new Socket(InetAddress.getByName(args[0]), Integer.parseInt(args[1])))) {
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}
