package dmu.dmuclub.servlet.sign;

import dmu.dmuclub.dto.member.MemberDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class SessionTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션에서 이메일 값을 가져옴
        MemberDto member = (MemberDto) req.getSession().getAttribute("member");

        // 콘솔에 출력
        System.out.println("User Email from Session: " + member.getEmail());

        // HTTP 응답에 이메일 값을 포함하여 클라이언트에게 보냄
        resp.setContentType("text/plain");
        resp.getWriter().write("User Email from Session: " + member.getEmail());
    }
}

