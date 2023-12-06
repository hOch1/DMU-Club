package dmu.dmuclub.servlet.admin;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.member.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/member")
public class MemberManagementServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<MemberDto> memberDtoList = memberService.findAll();

            request.setAttribute("memberList", memberDtoList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/adminMember2.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
