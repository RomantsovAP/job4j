package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Консольный чат с ботом
 * @author AlekseyRomantsov
 * @version 1.0.0.0
 * @since 11.03.2019
 */
public class ConsoleChat {

    private static final String FILE_PHRASES = "C:\\tt\\123.txt";
    private static final String FILE_LOG = "C:\\tt\\log.txt";
    private Bot bot;
    private Log log;

    public static void main(String[] args) {
        try {
            ConsoleChat chat = new ConsoleChat(
                    new FileBot(new File(FILE_PHRASES)),
                    new FileLog(Paths.get(FILE_LOG)));
            chat.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConsoleChat(Bot bot, Log log) {
        this.bot = bot;
        this.log = log;
    }

    public void start() {
        String userInput;
        System.out.println("chat:");
        try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in))) {
            //Inner assignments should be avoided.
            do {
                userInput = bufReader.readLine();
                if (userInput.equals("стоп") || userInput.equals("продолжить")) {
                    bot.toggle();
                }
                log.log(userInput);
                if (bot.isActive() && !"закончить".equals(userInput)) {
                    String botReply = bot.reply();
                    System.out.println(botReply);
                    log.log(botReply);
                }
            } while (!userInput.equals("закончить"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bot.release();
            log.close();
        }
    }

}
