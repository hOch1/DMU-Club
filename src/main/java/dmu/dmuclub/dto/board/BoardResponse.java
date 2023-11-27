package dmu.dmuclub.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.simple.JSONObject;

@Data
@AllArgsConstructor
public class BoardResponse {
    private String msg;
    private String code;

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("msg", this.msg);
        json.put("code", this.code);
        return json;
    }

    public static BoardResponse successResponse(){
        return new BoardResponse("success", "200");
    }
}
