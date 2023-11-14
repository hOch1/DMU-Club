package dmu.dmuclub.servlet.sign;

import dmu.dmuclub.dto.sign.SignUpResquest;
import dmu.dmuclub.service.sign.SignService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "SignUpServlet", value = "/sign-up")
public class SignUpServlet extends HttpServlet {

    private final SignService signService;

    public SignUpServlet(SignService signService) {
        this.signService = signService;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contentType = request.getContentType();

        if (contentType != null && contentType.contains("application/json")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

            JSONParser parser = new JSONParser();
            try {
                JSONObject object = (JSONObject) parser.parse(reader);

                SignUpResquest signUpResquest = createSignUpRequest(object, new SignUpResquest());

                signService.signUp(signUpResquest);

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private SignUpResquest createSignUpRequest(JSONObject object, SignUpResquest signUpResquest){
        signUpResquest.setEmail(object.get("email").toString());
        signUpResquest.setPassword(object.get("password").toString());
        signUpResquest.setNickname(object.get("nickname").toString());
        signUpResquest.setPhone(object.get("phone").toString());
        signUpResquest.setUsername(object.get("username").toString());

        return signUpResquest;
    }
}
