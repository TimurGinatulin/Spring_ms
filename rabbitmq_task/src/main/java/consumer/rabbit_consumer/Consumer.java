package consumer.rabbit_consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Consumer {
    private static final String EXCHANGE_NAME = "directExchanger";
    private String queueName;
    private Channel channel;
    private String key;


    public void receiveMessage(String key) throws IOException {
        this.key = key;
        openConnection();
        channel.queueBind(queueName, EXCHANGE_NAME, key);
        System.out.println(" [*] Waiting for messages");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }


    public void close(){
        try {
            channel.close();
            System.out.println("Chanel closed");
        } catch (Exception e) {
            System.out.println("Closed failed");
        }
    }

    public void retry() {
        try {
            openConnection();
            receiveMessage(key);
            System.out.println("Retry");
        } catch (Exception e) {
            System.out.println("Can't retry connection");
        }
    }

    private void openConnection() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection  connection = factory.newConnection();
            channel = connection.createChannel();
            queueName = channel.queueDeclare().getQueue();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        } catch (Exception e) {
            System.out.println("Can't open connection");
        }
    }

}
