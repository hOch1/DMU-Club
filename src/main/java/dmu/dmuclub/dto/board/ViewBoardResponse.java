package dmu.dmuclub.dto.board;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.simple.JSONObject;

@Data
@AllArgsConstructor
public class ViewBoardResponse {

    private String title;
    private String content;
    private String createDate;
    private String author;

    public static ViewBoardResponse toResponse(ViewBoardRequest boardRequest, String author){
        return new ViewBoardResponse(
                boardRequest.getTitle(),
                boardRequest.getContent(),
                boardRequest.getCreateDate(),
                author);
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("content", content);
        json.put("createDate", createDate);
        json.put("author", author);
        return json;
    }
}
