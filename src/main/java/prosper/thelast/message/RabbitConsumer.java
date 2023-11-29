package prosper.thelast.message;
import com.rabbitmq.client.*;

public class RabbitConsumer {
    private final static String QUEUE_NAME = "my-queue";

    public static void main(String[] args) throws Exception {
        // Creating a new connection factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Setting the host for the RabbitMQ server

        // Infinite loop to continuously listen for messages
        while (true) {
            // Using try-with-resources to automatically close resources when done
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {

                // Declaring the queue with durable, non-exclusive, non-auto-delete, and no additional arguments
                channel.queueDeclare(QUEUE_NAME, true, false, false, null);

                // Callback function to handle incoming messages
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    // Converting the message body to a string using UTF-8 encoding
                    String message = new String(delivery.getBody(), "UTF-8");
                    // Printing the received message
                    System.out.println("Message received: '" + message + "'");
                };

                // Setting up the consumer with automatic acknowledgment
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
                    // Empty callback for cancellation; not used in this example
                });

                // Printing a message indicating that the consumer has started and is waiting for messages
                System.out.println("Consumer started. Waiting for messages...");

                // Keeping the consumer running indefinitely
                Thread.sleep(Long.MAX_VALUE);
            }
        }
    }

}
