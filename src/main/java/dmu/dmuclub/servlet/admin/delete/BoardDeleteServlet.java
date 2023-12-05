package dmu.dmuclub.servlet.admin.delete;

import dmu.dmuclub.service.board.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/board/delete")
public class BoardDeleteServlet extends HttpServlet {

    private final BoardService boardService = new BoardService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String board_id = req.getParameter("id");
            boardService.deleteById(board_id);

            req.getRequestDispatcher("/admin").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
