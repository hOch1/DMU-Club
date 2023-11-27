package dmu.dmuclub.servlet.board;

import dmu.dmuclub.dto.board.ViewBoardResponse;
import dmu.dmuclub.exception.board.BoardNotFoundException;
import dmu.dmuclub.service.board.BoardService;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String boardId = request.getParameter("id");
            ViewBoardResponse boardResponse = boardService.viewBoardDetail(boardId);

            request.setAttribute("board", boardResponse);

            // 임시 Response
            RequestDispatcher dispatcher = request.getRequestDispatcher("/boardDetail.jsp");
            dispatcher.forward(request, response);
        } catch (RuntimeException | SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
