package ml.sadriev.kafka.client;

import java.util.Scanner;
import javax.annotation.Resource;
import ml.sadriev.kafka.services.ChatServices;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class Chat {

    private static final String EXIT_COMMAND = "exit";
    private static final String BROADCAST_MESSAGE = "bc";
    private static final String PRIVATE_MESSAGE = "pv";
    private static final String LOGIN_TEXT = "LOGIN: ";
    private static final String CHAT_IS_CLOSED_TEXT = "LOGOUT.";

    @Resource
    private ChatServices chatServices;
    @Resource
    private ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;

    public void start() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println(LOGIN_TEXT);
        final String login = scanner.nextLine();

        if (isBlank(login)) {
            System.out.print(CHAT_IS_CLOSED_TEXT);
            System.exit(-1);
        }

        chatServices.getContainer(login);
        System.out.println("Hello, " + login + "!");

        String command = "";
        while (!EXIT_COMMAND.equals(command)) {
            System.out.println("Enter command: bc, pv or exit");
            command = scanner.nextLine();

            if (BROADCAST_MESSAGE.equals(command)) {
                chatServices.doBroadcastMessage(scanner);
            } else if (PRIVATE_MESSAGE.equals(command)) {
                chatServices.doPrivateMessage(scanner);
            }
        }
        System.out.println("Chat is closed.");
        System.exit(0);
    }
}
