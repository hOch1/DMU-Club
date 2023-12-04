package dmu.dmuclub.servlet.member;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.exception.member.MemberNotFoundException;
import dmu.dmuclub.service.member.MemberService;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/info")
public class MyPageServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String memberId = request.getParameter("id");
            MemberDto memberDto = memberService.findMember(memberId);

            if (memberDto == null)
                throw new MemberNotFoundException("회원을 찾지 못하였습니다");

            request.setAttribute("member", memberDto);
            request.getRequestDispatcher("info/info.jsp").forward(request, response);
        } catch (SQLException | MemberNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
