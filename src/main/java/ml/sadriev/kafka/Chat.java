package ml.sadriev.kafka;

import java.util.Arrays;
import java.util.Scanner;
import javax.annotation.Resource;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class Chat {

    private static final String EXIT_COMMAND = "exit";
    private static final String BROADCAST_MESSAGE = "bc";
    private static final String PRIVATE_MESSAGE = "pv";
    private static final String ENTER_BROADCAST_MESSAGE_TEXT = "Enter broadcast message: ";
    private static final String ENTER_PRIVATE_MESSAGE_TEXT = "Enter private message: ";
    private static final String ENTER_USER_NAME_TEXT = "Enter user name: ";
    private static final String LOGIN_TEXT = "login: ";
    private static final String CHAT_IS_CLOSED_TEXT = "Chat is closed";

    @Resource
    private ChatEngine chatEngine;
    @Resource
    private ConsumerFactory<String, String> consumerFactory;
/*    @Resource
    private ChatConsumer chatConsumer;*/

    private void loop() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println(LOGIN_TEXT);
        final String login = scanner.nextLine();

        if (isBlank(login)) {
            System.out.print(CHAT_IS_CLOSED_TEXT);
            System.exit(-1);
        }

        final Consumer<Long, String> consumer = new KafkaConsumer<>(consumerFactory.getConfigurationProperties());
        consumer.subscribe(Arrays.asList("topic-" + login, "broadcastTopic"));

        System.out.println("Hello, "+ login + "!");

        String command = "";
        while (!EXIT_COMMAND.equals(command)) {
            System.out.println("Enter command: bc, pv or exit");
            command = scanner.nextLine();

            if (BROADCAST_MESSAGE.equals(command)) {
                doBroadcastMessage(scanner);
            } else if (PRIVATE_MESSAGE.equals(command)) {
                doPrivateMessage(scanner);
            }
        }
        System.out.println("Chat is closed.");
        System.exit(0);
    }

    private void doPrivateMessage(Scanner scanner) {
        System.out.println(ENTER_USER_NAME_TEXT);
        final String userConsumer = scanner.nextLine();

        if (!isBlank(userConsumer)) {
            System.out.println(ENTER_PRIVATE_MESSAGE_TEXT);
            final String message = scanner.nextLine();
            chatEngine.sendMessage(message, userConsumer);
        }
    }

    private void doBroadcastMessage(Scanner scanner) {
        System.out.println(ENTER_BROADCAST_MESSAGE_TEXT);
        final String message = scanner.nextLine();
        chatEngine.sendMessage(message);
    }

    public void start() {
        loop();
    }
}
