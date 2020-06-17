package ml.sadriev.kafka;

import javax.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ChatEngine {

    private static final String TOPIC_PREFIX = "topic";
    private static final String BROADCAST_TOPIC = "broadcastTopic";

/*    public ChatEngine(String login) {
        this.login = login;
        this.topic = TOPIC_PREFIX + login;
        this.broadcastTopic = BROADCAST_TOPIC;
    }*/

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(BROADCAST_TOPIC, message);
    }

    public void sendMessage(String message, String userConsumer) {
        kafkaTemplate.send(TOPIC_PREFIX + userConsumer, message);
    }

    @KafkaListener(topics = BROADCAST_TOPIC)
    public void listen(String message) {
        System.out.println("Received broadcast message: " + message);
    }
}
