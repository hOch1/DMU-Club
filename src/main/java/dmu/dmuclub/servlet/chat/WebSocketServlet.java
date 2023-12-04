package dmu.dmuclub.servlet.chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import java.io.IOException;

@WebServlet("/message")
public class WebSocketServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // 서블릿 초기화 시 WebSocketHandler 등록
        ServerContainer serverContainer = (ServerContainer) getServletContext().getAttribute(ServerContainer.class.getName());
        try {
            serverContainer.addEndpoint(MyWebSocketHandler.class);
        } catch (DeploymentException e) {
            throw new ServletException("Failed to add WebSocket endpoint", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        req.getRequestDispatcher("/message/message.jsp").forward(req, resp);
    }
}
