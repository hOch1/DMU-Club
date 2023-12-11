package dmu.dmuclub.dto.chat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatLogDto {
    private int id;
    private int chat_id;
    private String message;
    private LocalDateTime sendTime;
    private boolean myText;
}
