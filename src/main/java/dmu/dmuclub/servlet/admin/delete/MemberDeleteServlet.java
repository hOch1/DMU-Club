package dmu.dmuclub.servlet.admin.delete;

import dmu.dmuclub.service.member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/member/delete")
public class MemberDeleteServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String member_id = req.getParameter("id");

            memberService.deleteMember(member_id);

            req.getRequestDispatcher("/admin").forward(req, resp);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
