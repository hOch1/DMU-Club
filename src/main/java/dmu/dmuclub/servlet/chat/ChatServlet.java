package dmu.dmuclub.servlet.chat;

import dmu.dmuclub.dto.chat.ChatDto;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.chat.ChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/message")
public class ChatServlet extends HttpServlet {

    private final ChatService chatService = new ChatService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        try {
            HttpSession session = req.getSession();
            String nickname = req.getParameter("nickname");
            MemberDto memberDto = (MemberDto) session.getAttribute("member");
            List<MemberDto> chatDtoList = chatService.findChat(memberDto.getId());

            req.setAttribute("nickname", nickname);
            req.setAttribute("chatList", chatDtoList);
            req.getRequestDispatcher("/message/test.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
