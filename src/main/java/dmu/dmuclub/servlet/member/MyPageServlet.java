package dmu.dmuclub.servlet.member;

import dmu.dmuclub.dto.friend.FriendDto;
import dmu.dmuclub.dto.img.ImgDto;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.exception.member.MemberNotFoundException;
import dmu.dmuclub.service.friend.FriendService;
import dmu.dmuclub.service.member.MemberService;
import dmu.dmuclub.service.member.ProfileService;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/info")
public class MyPageServlet extends HttpServlet {

    private final FriendService friendService = new FriendService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            MemberDto memberDto = (MemberDto) session.getAttribute("member");

            if (memberDto == null)
                throw new MemberNotFoundException("회원을 찾지 못하였습니다");

            List<MemberDto> friendList = friendService.findFriends(memberDto.getId());

            request.setAttribute("friendList", friendList);
            request.setAttribute("member", memberDto);
            request.getRequestDispatcher("info/info.jsp").forward(request, response);
        } catch (MemberNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
