package producer;

import producer.rabbit_producer.Producer;
import producer.test_generator.MessageGenerator;

public class ProducerMain {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            Producer.sendMessage(MessageGenerator.generateMessage());
        }
    }

}
