package ml.sadriev.kafka;

import ml.sadriev.kafka.client.Chat;
import ml.sadriev.kafka.context.ContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        Chat chat = context.getBean(Chat.class);
        chat.start();
    }
}
