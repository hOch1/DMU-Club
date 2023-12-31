package dmu.dmuclub.servlet.chat;

import dmu.dmuclub.dto.chat.ChatLogDto;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    private final ChatService chatService = new ChatService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            MemberDto memberDto = (MemberDto) session.getAttribute("member");

            List<MemberDto> chatList = chatService.findChatList(memberDto.getId());

            req.setAttribute("chatList", chatList);
            req.setAttribute("member", memberDto);
            req.getRequestDispatcher("/message/message.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ChatLogDto> createFinalLog(List<ChatLogDto> sendLog, List<ChatLogDto> toLog){
        List<ChatLogDto> finalLog = new ArrayList<>();
        finalLog.addAll(sendLog);
        finalLog.addAll(toLog);

        Collections.sort(finalLog, Comparator.comparing(ChatLogDto::getSendTime));
        return finalLog;
    }
}
