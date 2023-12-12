package dmu.dmuclub.common;

import dmu.dmuclub.dto.chat.MessageDto;
import org.json.simple.JSONObject;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<MessageDto> {

    @Override
    public String encode(MessageDto object) throws EncodeException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("member_id", object.getMember_id());
        jsonObject.put("msg", object.getMsg());

        return jsonObject.toJSONString();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
