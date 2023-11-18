package dmu.dmuclub.dto.board;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewBoardRequest {

    private String title;
    private String content;
    private String createDate;
    private String memberId;

    @Builder
    public ViewBoardRequest(String title, String content, String createDate, String memberId) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.memberId = memberId;
    }

}
