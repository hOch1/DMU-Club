package dmu.dmuclub.servlet.board;

import dmu.dmuclub.dto.board.ViewBoardResponse;
import dmu.dmuclub.service.board.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/board/view")
public class ViewBoardServlet extends HttpServlet {

    private final BoardService boardService;

    public ViewBoardServlet() {
        boardService = new BoardService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String boardId = request.getParameter("id");
        ViewBoardResponse boardResponse = null;

        try {
            boardResponse = boardService.viewBoard(boardId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(boardResponse.toJson().toJSONString());
    }
}
