package ml.sadriev.kafka;

import javax.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChatConsumer {
    @Resource
    private ConsumerFactory<String, String> consumerFactory;

    @Getter
    @Setter
    String login;

    @Getter
    @Setter
    Consumer<Long, String> consumer;

    public ChatConsumer() {
//        this.login = "assa";
//        this.consumer = new KafkaConsumer<>(consumerFactory.getConfigurationProperties());
    }
}
