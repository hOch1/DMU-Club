package dmu.dmuclub.servlet.sign;

import dmu.dmuclub.dto.sign.SignInRequest;
import dmu.dmuclub.service.sign.SignService;

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

            // 임시 Response
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("로그인 완료");
        } catch (RuntimeException | SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private SignInRequest createSignInRequest(HttpServletRequest request) {
        return new SignInRequest(
                request.getParameter("email"),
                request.getParameter("password"));
    }
}
