package dmu.dmuclub.common;

import dmu.dmuclub.config.ChatConfig;
import dmu.dmuclub.dto.member.MemberDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.*;

@ServerEndpoint(value = "/messagePoint", configurator = ChatConfig.class)
public class WebSocket {

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
    private HttpSession httpSession;


    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.httpSession = (HttpSession) config.getUserProperties().get("httpSession");
        clients.add(session);
    }

    @OnMessage
    public void onMessage(String message) {
        synchronized (clients){
            MemberDto sendMember = (MemberDto) httpSession.getAttribute("member");

            try {
                for (Session client : clients) {
                    HttpSession session = (HttpSession) client.getUserProperties().get("httpSession");
                    MemberDto memberDto = (MemberDto) session.getAttribute("member");

                    String msg = "[" + sendMember.getNickname() + "] : " + message;
                    client.getBasicRemote().sendText(msg);
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
