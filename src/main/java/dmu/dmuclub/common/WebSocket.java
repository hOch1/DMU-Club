package dmu.dmuclub.common;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.*;

@ServerEndpoint("/messageServer")
public class WebSocket {

    private static Set<Session> clients = synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session){
        clients.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException{
        synchronized (clients){
            for (Session client : clients){
                if (!client.equals(session))
                    client.getBasicRemote().sendText(message);
            }
        }
    }

    @OnClose
    public void onClose(Session session){
        clients.remove(session);
    }

    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }
}
