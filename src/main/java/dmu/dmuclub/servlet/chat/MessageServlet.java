package dmu.dmuclub.servlet.chat;

import dmu.dmuclub.dto.chat.ChatLogDto;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.chat.ChatService;
import dmu.dmuclub.service.member.MemberService;

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

@WebServlet("/message")
public class MessageServlet extends HttpServlet {

    private final ChatService chatService = new ChatService();
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            String nickname = req.getParameter("nickname");
            MemberDto memberDto = (MemberDto) session.getAttribute("member");
            MemberDto toMember = memberService.findMember_nickname(nickname);

            List<MemberDto> chatList = chatService.findChatList(memberDto.getId());

            List<ChatLogDto> sendLog = chatService.findChatLog(memberDto.getId(), toMember.getId());
            List<ChatLogDto> toLog = chatService.findChatLog(toMember.getId(), memberDto.getId());


            req.setAttribute("logs", createFinalLog(sendLog, toLog));
            req.setAttribute("nickname", nickname);
            req.setAttribute("chatList", chatList);
            req.setAttribute("member", memberDto);
            req.getRequestDispatcher("/message/chat.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ChatLogDto> createFinalLog(List<ChatLogDto> sendLog, List<ChatLogDto> toLog){
        List<ChatLogDto> finalLog = new ArrayList<>();

        for (ChatLogDto chatLogDto : sendLog)
            chatLogDto.setMyText(true);

        for (ChatLogDto chatLogDto : toLog)
            chatLogDto.setMyText(false);

        finalLog.addAll(sendLog);
        finalLog.addAll(toLog);

        Collections.sort(finalLog, Comparator.comparing(ChatLogDto::getSendTime));
        return finalLog;
    }
}
