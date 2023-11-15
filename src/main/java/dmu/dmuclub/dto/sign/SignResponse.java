package dmu.dmuclub.dto.sign;

import org.json.simple.JSONObject;

public class SignResponse {

    private String msg;
    private String code;

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("msg", this.msg);
        json.put("code", this.code);
        return json;
    }

    public SignResponse() {
    }

    public SignResponse(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
