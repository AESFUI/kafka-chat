package ml.sadriev.kafka;

import java.util.Scanner;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Chat {

    private static final String EXIT_COMMAND = "exit";
    private static final String BROADCAST_MESSAGE = "bc";
    private static final String PRIVATE_MESSAGE = "pv";
    private static final String ENTER_BROADCAST_MESSAGE_TEXT = "Enter broadcast message: ";
    private static final String ENTER_PRIVATE_MESSAGE_TEXT = "Enter private message: ";
    private static final String ENTER_USER_NAME_TEXT = "Enter user name: ";
    private static final String LOGIN_TEXT = "login: ";
    private static final String CHAT_IS_CLOSED_TEXT = "Chat is closed";

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.println(LOGIN_TEXT);
        final String login = scanner.nextLine();

        if (isBlank(login)) {
            System.out.print(CHAT_IS_CLOSED_TEXT);
            System.exit(-1);
        }

        final ChatEngine chat = ChatEngine.createChat(login);

        System.out.println("Hello, "+ login + "!\nAre you ready for messaging?");

        String command = "";
        while (!EXIT_COMMAND.equals(command)) {
            command = scanner.nextLine();

            if (BROADCAST_MESSAGE.equals(command)) {
                System.out.println(ENTER_BROADCAST_MESSAGE_TEXT);
                final String message = scanner.nextLine();


                continue;
            }

            if (PRIVATE_MESSAGE.equals(command)) {
                System.out.println(ENTER_PRIVATE_MESSAGE_TEXT);
                final String message = scanner.nextLine();
                System.out.println(ENTER_USER_NAME_TEXT);

                continue;
            }
        }
        System.out.println("Chat is closed.");
        System.exit(0);
    }
}
