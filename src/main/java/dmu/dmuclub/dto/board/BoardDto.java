package dmu.dmuclub.dto.board;

import org.json.simple.JSONObject;

import java.time.LocalDateTime;

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

    public BoardDto() {
    }

    public BoardDto(String id, String title, String content, String createDate, int member_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.member_id = member_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
}
