package ml.sadriev.kafka;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage implements Serializable {
    private String message;

    private String loginFrom;

    @Override
    public String toString() {
        return "message='" + message + '\'' + ", loginFrom='" + loginFrom + '\'' + '}';
    }
}
