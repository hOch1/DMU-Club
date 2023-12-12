package dmu.dmuclub.common;

import dmu.dmuclub.config.ChatConfig;
import dmu.dmuclub.dto.chat.ChatDto;
import dmu.dmuclub.dto.chat.ChatLogDto;
import dmu.dmuclub.dto.chat.MessageDto;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.chat.ChatService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.*;

@ServerEndpoint(value = "/messagePoint/{nickname}", configurator = ChatConfig.class, encoders = MessageEncoder.class)
public class WebSocket {

    private final ChatService chatService = new ChatService();
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
    private HttpSession httpSession;


    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws SQLException {
        this.httpSession = (HttpSession) config.getUserProperties().get("httpSession");
        clients.add(session);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("nickname") String nickname) {
        synchronized (clients){
            MemberDto sendMember = (MemberDto) httpSession.getAttribute("member");

            try {
                for (Session client : clients) {
                    HttpSession toSession = (HttpSession) client.getUserProperties().get("httpSession");
                    MemberDto memberDto = (MemberDto) toSession.getAttribute("member");
                    MessageDto msg = new MessageDto();
                    msg.setMember_id(sendMember.getId());
                    msg.setMsg(message);
                    if (memberDto.getNickname().equals(nickname) || memberDto.getNickname().equals(sendMember.getNickname())) {
                        client.getBasicRemote().sendObject(msg);
                        if (memberDto.getNickname().equals(sendMember.getNickname()))
                            chatService.WriteChatLog(message, sendMember);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable t){
        t.printStackTrace();
        clients.remove(session);
    }
}
