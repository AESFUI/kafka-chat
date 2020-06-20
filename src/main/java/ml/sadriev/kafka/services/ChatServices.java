package ml.sadriev.kafka.services;

import java.util.Scanner;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import ml.sadriev.kafka.config.MyMessageListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
@Setter
@Service
public class ChatServices {

    public static final String TOPIC_PREFIX = "topic-";
    public static final String BROADCAST_TOPIC = "broadcastTopic";

    private static final String ENTER_BROADCAST_MESSAGE_TEXT = "Enter broadcast message: ";
    private static final String ENTER_PRIVATE_MESSAGE_TEXT = "Enter private message: ";
    private static final String ENTER_USER_NAME_TEXT = "Enter recipient user name: ";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    @Resource
    private ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;

    public void sendMessage(String message) {
        kafkaTemplate.send(BROADCAST_TOPIC, message);
    }

    public void sendMessage(String message, String userConsumer) {
        kafkaTemplate.send(TOPIC_PREFIX + userConsumer, message);
    }

    public void doPrivateMessage(Scanner scanner) {
        System.out.println(ENTER_USER_NAME_TEXT);
        final String userConsumer = scanner.nextLine();

        if (!isBlank(userConsumer)) {
            System.out.println(ENTER_PRIVATE_MESSAGE_TEXT);
            final String message = scanner.nextLine();
            sendMessage(message, userConsumer);
        }
    }

    public void doBroadcastMessage(Scanner scanner) {
        System.out.println(ENTER_BROADCAST_MESSAGE_TEXT);
        final String message = scanner.nextLine();
        sendMessage(message);
    }

    public void getContainer(String login) {
        ConcurrentMessageListenerContainer<String, String> container = kafkaListenerContainerFactory
                .createContainer(BROADCAST_TOPIC, "topic-" + login);
        container.getContainerProperties().setGroupId(UUID.randomUUID().toString());
        container.getContainerProperties().setMessageListener(new MyMessageListener());
        container.start();
    }
}
