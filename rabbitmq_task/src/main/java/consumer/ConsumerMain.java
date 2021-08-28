package consumer;


import consumer.rabbit_consumer.Consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsumerMain {
    private static final Consumer consumer;
    private static final Scanner scanner;
    private static final List<String> themes;
    private static boolean exit = false;

    static {
        System.out.println("Initialization....");
        scanner = new Scanner(System.in);
        consumer = new Consumer();
        themes = new ArrayList<>();
        themes.add("java");
        themes.add("php");
        themes.add("java_script");
        themes.add("python");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to RabbitReader");
        System.out.println("Choice theme");
        while (!exit) {
            String answer = scanner.nextLine();
            parseAnswer(answer);
        }

    }

    private static void parseAnswer(String answer) {
        switch (answer) {
            case "close": {
                consumer.close();
                break;
            }
            case "retry": {
                consumer.retry();
                break;
            }
            case "exit": {
                exit = true;
                break;
            }
            default: {
                if (themes.contains(answer)) {
                    try {
                        consumer.receiveMessage(answer);
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
