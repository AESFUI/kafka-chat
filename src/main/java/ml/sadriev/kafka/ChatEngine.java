package ml.sadriev.kafka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatEngine {

    String login;

    public ChatEngine(String login) {
        this.login = login;
    }

    public static ChatEngine createChat(String login) {
        return new ChatEngine(login);
    }
}
