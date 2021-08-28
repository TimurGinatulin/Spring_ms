package producer.test_generator;

import producer.rabbit_producer.models.Message;

import java.util.Random;

public class MessageGenerator {
    private final static String[] themes = {"java", "php", "java_script", "python"};
    private final static Random random;

    static {
        random = new Random();
    }

    public static Message generateMessage() {
        return new Message(themes[random.nextInt(4)], "Random test message", "Randomizer");
    }
}
