package dmu.dmuclub.dto.board;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Data
@NoArgsConstructor
public class CreateBoardRequest {

    private String title;
    private String content;
    private int member_id;


    public static CreateBoardRequest toDto(JSONObject json){
        CreateBoardRequest boardDto = new CreateBoardRequest();
        boardDto.title = json.get("title").toString();
        boardDto.content = json.get("content").toString();

        return boardDto;
    }
}
