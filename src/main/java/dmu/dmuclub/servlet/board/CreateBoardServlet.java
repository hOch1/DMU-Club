package dmu.dmuclub.servlet.board;

import dmu.dmuclub.dto.board.BoardResponse;
import dmu.dmuclub.dto.board.CreateBoardRequest;
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

    private final BoardService boardService = new BoardService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            boardService.createBoard(CreateBoardRequest.toDto(request), session);

            // 임시 Response
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("게시물 생성 완료");
        } catch (RuntimeException | SQLException | ClassNotFoundException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
