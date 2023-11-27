package dmu.dmuclub.servlet.admin;

import dmu.dmuclub.dto.board.ViewBoardResponse;
import dmu.dmuclub.service.board.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/board")
public class BoardManagementServlet extends HttpServlet {

    private final BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ViewBoardResponse> boardResponses = boardService.viewBoardAll();

        request.setAttribute("boardList", boardResponses);
        // 임시 response
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/adminBoard.jsp");
        dispatcher.forward(request, response);
    }
}
