package ml.sadriev.kafka;

import javax.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ChatEngine {

    private static final String TOPIC_PREFIX = "topic-";
    private static final String BROADCAST_TOPIC = "broadcastTopic";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(BROADCAST_TOPIC, message);
    }

    public void sendMessage(String message, String userConsumer) {
        kafkaTemplate.send(TOPIC_PREFIX + userConsumer, message);
    }

    @KafkaListener(topics = BROADCAST_TOPIC)
    public void listenBC(String message) {
        System.out.println("Received broadcast message: " + message);
    }

//    @KafkaListener(topics = "#{chatConsumer.getLogin()}")
    @KafkaListener(topics = "topic-saas")
    public void listenPV(ConsumerRecord<String, String> record) {
        System.out.println("Received private message from : " + record.value());
    }
}
