package producer.rabbit_producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import producer.rabbit_producer.models.Message;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static final String EXCHANGE_NAME = "directExchanger";
    private static final ConnectionFactory factory;
    private static final ObjectWriter writer;

    static {
        writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
    }

    public static void sendMessage(Message message) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            String stringJson = writer.writeValueAsString(message);
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            channel.basicPublish(EXCHANGE_NAME, message.getTheme(), null, stringJson.getBytes());
            System.out.println("[X]Send: " + stringJson);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}
