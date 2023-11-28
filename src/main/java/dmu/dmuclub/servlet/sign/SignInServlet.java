package dmu.dmuclub.servlet.sign;

import dmu.dmuclub.dto.sign.SignInRequest;
import dmu.dmuclub.service.sign.SignService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/auth/sign-in")
public class SignInServlet extends HttpServlet {

    private final SignService signService = new SignService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            signService.signIn(createSignInRequest(request), session);

            request.getSession().setAttribute("message", "로그인이 성공적으로 완료되었습니다.");
            response.sendRedirect("/");
        } catch (RuntimeException | SQLException e) {
            request.getSession().setAttribute("message", "로그인에 실패했습니다.");
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("signIn.jsp");
    }

    private SignInRequest createSignInRequest(HttpServletRequest request) {
        return new SignInRequest(
                request.getParameter("email"),
                request.getParameter("password"));
    }
}
