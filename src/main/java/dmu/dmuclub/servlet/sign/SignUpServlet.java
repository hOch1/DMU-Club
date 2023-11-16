package dmu.dmuclub.servlet.sign;

import dmu.dmuclub.dto.Response;
import dmu.dmuclub.dto.sign.SignUpResquest;
import dmu.dmuclub.service.sign.SignService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "SignUpServlet", value = "/auth/sign-up")
public class SignUpServlet extends HttpServlet {

    private final SignService signService;

    public SignUpServlet() {
        signService = new SignService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contentType = request.getContentType();

        if (contentType != null && contentType.contains("application/json")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            JSONParser parser = new JSONParser();

            try {
                JSONObject requestJson = (JSONObject) parser.parse(reader);
                SignUpResquest signUpResquest = createSignUpRequest(requestJson);
                Response signResponse = signService.signUp(signUpResquest);


                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(signResponse.toJson().toJSONString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private SignUpResquest createSignUpRequest(JSONObject requestJson){
        return SignUpResquest.builder()
                .email(requestJson.get("email").toString())
                .password(requestJson.get("password").toString())
                .phone(requestJson.get("phone").toString())
                .nickname(requestJson.get("nickname").toString())
                .username(requestJson.get("username").toString())
                .build();
    }
}
