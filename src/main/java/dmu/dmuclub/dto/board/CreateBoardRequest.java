package dmu.dmuclub.dto.board;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Data
@NoArgsConstructor
public class CreateBoardRequest {

    private String title;
    private String content;
    private int member_id;

}
