package dmu.dmuclub.servlet.member.friend;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.friend.FriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/member/refuseFriend")
public class RefuseFriendServlet extends HttpServlet {
    private final FriendService friendService = new FriendService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            MemberDto memberDto = (MemberDto) session.getAttribute("member");
            int id = Integer.parseInt(req.getParameter("id"));

            friendService.refuseFried(id, memberDto.getId());
            resp.sendRedirect("/main");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
