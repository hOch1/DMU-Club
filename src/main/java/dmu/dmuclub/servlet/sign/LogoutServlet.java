package dmu.dmuclub.servlet.sign;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        req.getSession().removeAttribute("member");

        // 임시 Response
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("로그아웃 완료");
    }
}
