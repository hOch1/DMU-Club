package dmu.dmuclub.servlet.board;

import dmu.dmuclub.dto.board.ViewBoardResponse;
import dmu.dmuclub.exception.board.BoardNotFoundException;
import dmu.dmuclub.service.board.BoardService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/board/detail")
public class ViewDetailBoardServlet extends HttpServlet {

    private final BoardService boardService;

    public ViewDetailBoardServlet() {
        boardService = new BoardService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String boardId = request.getParameter("id");
            ViewBoardResponse boardResponse = boardService.viewBoardDetail(boardId);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(boardResponse.toJson().toJSONString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (BoardNotFoundException e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", e.getMessage());
            jsonObject.put("code", "404");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonObject.toJSONString());
        }
    }
}
