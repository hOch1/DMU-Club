package dmu.dmuclub;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.friend.FriendService;
import dmu.dmuclub.service.member.MemberService;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/main")
public class IndexServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final FriendService friendService = new FriendService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            MemberDto memberDto = (MemberDto) session.getAttribute("member");

            if (memberDto != null) {
                if(memberDto.getRole().equals("ADMIN"))
                    response.sendRedirect("/admin");
                else {
                    List<MemberDto> memberDtoList = memberService.matchMember(memberDto.getMbti());
                    List<MemberDto> askFriends = friendService.findAskFriend(memberDto.getId());

                    if (memberDtoList.isEmpty())
                        session.setAttribute("memberList", null);
                    else
                        session.setAttribute("memberList", memberDtoList);

                    if (askFriends.isEmpty())
                        session.setAttribute("askFriend", null);
                    else
                        session.setAttribute("askFriend", askFriends);

                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }else {
                response.sendRedirect("/auth/sign-in");
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}