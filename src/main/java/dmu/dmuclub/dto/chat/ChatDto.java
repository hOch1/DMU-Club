package dmu.dmuclub.dto.chat;

import lombok.Data;

@Data
public class ChatDto {
    private int id;
    private int from_member;
    private int to_member;
}
