package dmu.dmuclub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Data
@AllArgsConstructor
public class Response {

    private String msg;
    private String code;

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("msg", this.msg);
        json.put("code", this.code);
        return json;
    }

    public static Response successResponse(){
        return new Response("success", "200");
    }
}
