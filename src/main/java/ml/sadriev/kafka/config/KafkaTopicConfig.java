package ml.sadriev.kafka.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import static ml.sadriev.kafka.services.ChatServices.BROADCAST_TOPIC;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "localhost:9092")
    private String serverAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, serverAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic broadcastTopic() {
        return new NewTopic(BROADCAST_TOPIC, 1, (short) 1);
    }
}
