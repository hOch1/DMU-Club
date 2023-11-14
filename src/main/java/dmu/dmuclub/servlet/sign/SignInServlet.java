package dmu.dmuclub.servlet.sign;

import dmu.dmuclub.dto.sign.SignInRequest;
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

@WebServlet(name = "SignInServlet", value = "/sign-in")
public class SignInServlet extends HttpServlet {

    private final SignService signService;

    public SignInServlet(SignService signService) {
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

                SignInRequest signInRequest = createSignUpRequest(object, new SignInRequest());

                signService.signIn(signInRequest);

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private SignInRequest createSignUpRequest(JSONObject object, SignInRequest signInRequest) {

        signInRequest.setEmail(object.get("email").toString());
        signInRequest.setPassword(object.get("password").toString());

        return signInRequest;
    }
}
