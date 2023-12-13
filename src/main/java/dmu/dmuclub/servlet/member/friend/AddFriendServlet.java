package dmu.dmuclub.servlet.member.friend;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.friend.FriendService;
import dmu.dmuclub.service.member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/member/addfriend")
public class AddFriendServlet extends HttpServlet {

    private final FriendService friendService = new FriendService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            MemberDto memberDto = (MemberDto) session.getAttribute("member");

            int member_id = Integer.parseInt(req.getParameter("member_id"));
            friendService.addFriend(memberDto.getId(), member_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
