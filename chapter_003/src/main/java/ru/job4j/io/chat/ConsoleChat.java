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

    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String FINISH = "закончить";

    private Bot bot;
    private Log log;

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("You should specify 2 parameters^ 1-chatBot phrases file, 2- log-file");
        }
        try (Log log = new FileLog(Paths.get(args[1]))) {
            ConsoleChat chat = new ConsoleChat(new FileBot(new File(args[0])), log);
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
            do {
                userInput = bufReader.readLine();
                if (STOP.equals(userInput) || CONTINUE.equals(userInput)) {
                    bot.toggle();
                }
                log.log(userInput);
                if (bot.isActive() && !FINISH.equals(userInput)) {
                    String botReply = bot.reply();
                    System.out.println(botReply);
                    log.log(botReply);
                }
            } while (FINISH.equals(userInput));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bot.release();
        }
    }

}
