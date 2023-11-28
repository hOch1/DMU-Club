package dmu.dmuclub.dto.board;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Builder
    public ViewBoardResponse(int id, String title, String content, String createDate, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.author = author;
    }
}
