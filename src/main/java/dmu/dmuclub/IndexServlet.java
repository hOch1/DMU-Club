package dmu.dmuclub;

import dmu.dmuclub.dto.member.MemberDto;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/")
public class IndexServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("member");


        if (memberDto == null) {
            System.out.println("null " + memberDto);
            response.sendRedirect("/auth/signIn.jsp");
        }
        else {
            System.out.println("nickname " + memberDto.getNickname());
            response.sendRedirect("index.jsp");
        }
    }
}