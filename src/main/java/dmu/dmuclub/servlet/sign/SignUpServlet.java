package dmu.dmuclub.servlet.sign;

import dmu.dmuclub.dto.sign.SignResponse;
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

    public SignUpServlet() {
        this.signService = new SignService();
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
                JSONObject requestJson = (JSONObject) parser.parse(reader);
                SignUpResquest signUpResquest = createSignUpRequest(requestJson, new SignUpResquest());
                signService.signUp(signUpResquest);


                /**
                 * 임시 response
                 */
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                SignResponse signResponse = new SignResponse("success", "200");
                JSONObject jsonResponse = signResponse.toJson();

                response.getWriter().write(jsonResponse.toJSONString());

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private SignUpResquest createSignUpRequest(JSONObject requestJson, SignUpResquest signUpResquest){
        signUpResquest.setEmail(requestJson.get("email").toString());
        signUpResquest.setPassword(requestJson.get("password").toString());
        signUpResquest.setNickname(requestJson.get("nickname").toString());
        signUpResquest.setPhone(requestJson.get("phone").toString());
        signUpResquest.setUsername(requestJson.get("username").toString());

        return signUpResquest;
    }
}
