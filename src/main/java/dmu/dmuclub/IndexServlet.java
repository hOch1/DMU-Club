package dmu.dmuclub;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.exception.member.MemberNotFoundException;
import dmu.dmuclub.exception.sign.LoginFailureException;
import dmu.dmuclub.service.member.MemberService;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/main")
public class IndexServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            MemberDto memberDto = (MemberDto) session.getAttribute("member");

            if (memberDto != null) {
                List<MemberDto> memberDtoList = memberService.matchMember(memberDto.getMbti());
                session.setAttribute("memberList", memberDtoList);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }else if (memberDto.getRole().equals("ADMIN")){
                response.sendRedirect("/admin");
            }else {
                response.sendRedirect("/auth/sign-in");
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}