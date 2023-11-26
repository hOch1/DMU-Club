package dmu.dmuclub.servlet.sign;

import dmu.dmuclub.dto.sign.SignUpResquest;
import dmu.dmuclub.service.sign.SignService;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/auth/sign-up")
public class SignUpServlet extends HttpServlet {

    private SignService signService = new SignService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            signService.signUp(createSignUpRequest(request));


            // 임시 Response
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("회원가입 완료");
        } catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR ,e.getMessage());
        }
    }

    private SignUpResquest createSignUpRequest(HttpServletRequest request){
        return SignUpResquest.builder()
                .email(request.getParameter("email"))
                .username(request.getParameter("username"))
                .password(request.getParameter("password"))
                .nickname(request.getParameter("nickname"))
                .phone(request.getParameter("phone"))
                .build();
    }
}
