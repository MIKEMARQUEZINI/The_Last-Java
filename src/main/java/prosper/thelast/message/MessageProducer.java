package prosper.thelast.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Queue;

@Service
public class MessageProducer {
    // Autowired constructor injection for RabbitTemplate and Queue
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    // Method for sending a message to the specified queue
    public void sendMessage(String message){
        // Using RabbitTemplate to convert and send the message to the specified queue
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}
