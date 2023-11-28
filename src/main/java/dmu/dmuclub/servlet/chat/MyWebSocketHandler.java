package dmu.dmuclub.servlet.chat;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class MyWebSocketHandler {

    @OnOpen
    public void onOpen(Session session) {
        // 클라이언트가 웹 소켓에 연결되면 호출됩니다.
        System.out.println("WebSocket Opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 클라이언트로부터 메시지를 받으면 호출됩니다.
        System.out.println("Message from " + session.getId() + ": " + message);
    }

    @OnClose
    public void onClose(Session session) {
        // 클라이언트가 웹 소켓에서 연결을 종료하면 호출됩니다.
        System.out.println("WebSocket Closed: " + session.getId());
    }
}
