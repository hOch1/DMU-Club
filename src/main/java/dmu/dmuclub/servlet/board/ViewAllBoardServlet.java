package dmu.dmuclub.servlet.board;

import dmu.dmuclub.dto.board.ViewBoardResponse;
import dmu.dmuclub.exception.board.BoardNotFoundException;
import dmu.dmuclub.service.board.BoardService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board/all")
public class ViewAllBoardServlet extends HttpServlet {

    private final BoardService boardService;

    public ViewAllBoardServlet() {
        boardService = new BoardService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ViewBoardResponse> boardResponses = boardService.viewBoardAll();

            request.setAttribute("boardList", boardResponses);

            // 임시 Response
            RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardList.jsp");
            dispatcher.forward(request, response);
        } catch (BoardNotFoundException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
