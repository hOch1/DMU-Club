package dmu.dmuclub.dto.board;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardDto {

    private String id;
    private String title;
    private String content;
    private String createDate;
    private int member_id;


    public static BoardDto toDto(JSONObject json){
        BoardDto boardDto = new BoardDto();
        boardDto.title = json.get("title").toString();
        boardDto.content = json.get("content").toString();
        boardDto.createDate = LocalDateTime.now().toString();

        return boardDto;
    }
}
