package dmu.dmuclub.dto.board;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Data
@NoArgsConstructor
public class ViewBoardResponse {

    private int id;
    private String title;
    private String content;
    private String createDate;
    private String author;

    public ViewBoardResponse(String title, String content, String createDate, String author) {
    }

    public static ViewBoardResponse toResponse(ViewBoardRequest boardRequest, String author){
        return new ViewBoardResponse(
                boardRequest.getTitle(),
                boardRequest.getContent(),
                boardRequest.getCreateDate(),
                author);
    }

    @Builder
    public ViewBoardResponse(int id, String title, String content, String createDate, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.author = author;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("title", title);
        json.put("content", content);
        json.put("createDate", createDate);
        json.put("author", author);
        return json;
    }
}
