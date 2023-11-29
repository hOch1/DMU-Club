package dmu.dmuclub.servlet.sign;

import dmu.dmuclub.dto.sign.SignUpRequest;
import dmu.dmuclub.service.sign.SignService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/auth/sign-up")
public class SignUpServlet extends HttpServlet {

    private SignService signService = new SignService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println(createSignUpRequest(request).toString());
            signService.signUp(createSignUpRequest(request));
            request.getSession().setAttribute("message", "회원가입이 성공적으로 완료되었습니다.");
            response.sendRedirect("/");
        } catch (RuntimeException e){
            request.getSession().setAttribute("message", "회원가입도중 오류가 발생했습니다.");
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("signUp.jsp");
    }

    private SignUpRequest createSignUpRequest(HttpServletRequest request){
        return SignUpRequest.builder()
                .email(request.getParameter("email"))
                .username(request.getParameter("username"))
                .password(request.getParameter("password"))
                .nickname(request.getParameter("nickname"))
                .phone(request.getParameter("phone"))
                .mbti(createMBTI(request))
                .hobby(request.getParameter("hobby"))
                .build();
    }

    private String createMBTI(HttpServletRequest request){
        String EI = request.getParameter("EI");
        String SN = request.getParameter("SN");
        String FP = request.getParameter("FP");
        String JP = request.getParameter("JP");

        return EI+SN+FP+JP;
    }
}
