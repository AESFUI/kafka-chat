package ml.sadriev.kafka;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

@Component
public class ChatConsumer {

    @Getter
    @Setter
    String login;

    @Getter
    @Setter
    KafkaConsumer<String, String> consumer;

    public ChatConsumer() {
    }
}
