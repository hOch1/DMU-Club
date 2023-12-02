package dmu.dmuclub.servlet.chat;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chatSocket/{username}")
public class MyWebSocketHandler {

    private static final Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        // 클라이언트가 웹 소켓에 연결되면 호출됩니다.
        System.out.println("WebSocket Opened: " + session.getId() + " - User: " + username);
        sessions.put(username, session);
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        // 클라이언트로부터 메시지를 받으면 호출됩니다.
        System.out.println("Message from " + username + ": " + message);

        // 특정 사용자에게 메시지 전송
        Session userSession = sessions.get(username);
        if (userSession != null) {
            try {
                userSession.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        // 클라이언트가 웹 소켓에서 연결을 종료하면 호출됩니다.
        System.out.println("WebSocket Closed: " + session.getId() + " - User: " + username);
        sessions.remove(username);
    }
}
