package dmu.dmuclub.dto.friend;

import lombok.Data;

@Data
public class AskFriendDto {

    private int id;
    private int from_member;
    private int to_member;
}
