package dmu.dmuclub.servlet.board;

import dmu.dmuclub.dto.Response;
import dmu.dmuclub.dto.board.BoardDto;
import dmu.dmuclub.service.board.BoardService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

@WebServlet("/board/create")
public class CreateBoardServlet extends HttpServlet {

    private final BoardService boardService;

    public CreateBoardServlet(BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contentType = request.getContentType();

        if (contentType != null && contentType.contains("application/json")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            JSONParser parser = new JSONParser();

            try {
                JSONObject requestJson = (JSONObject) parser.parse(reader);
                HttpSession session = request.getSession();
                Response boardResponse = boardService.createBoard(BoardDto.toDto(requestJson), session);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(boardResponse.toJson().toJSONString());
            } catch (ParseException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
