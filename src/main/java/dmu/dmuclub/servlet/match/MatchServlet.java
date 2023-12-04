package dmu.dmuclub.servlet.match;

import dmu.dmuclub.service.member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.getRequestDispatcher("/matching/matching.jsp").forward(request, response);
    }
}
