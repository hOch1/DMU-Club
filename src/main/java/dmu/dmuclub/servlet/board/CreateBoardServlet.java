package dmu.dmuclub.servlet.board;

import dmu.dmuclub.dto.board.CreateBoardRequest;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.board.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/community/write")
public class CreateBoardServlet extends HttpServlet {

    private final BoardService boardService = new BoardService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            boardService.createBoard(createBoardRequest(request), session);

            response.sendRedirect("/board");
        } catch (RuntimeException | SQLException | ClassNotFoundException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/community/write.jsp").forward(req, resp);
    }

    private CreateBoardRequest createBoardRequest(HttpServletRequest request){
        CreateBoardRequest boardDto = new CreateBoardRequest();
        boardDto.setTitle(request.getParameter("title"));
        boardDto.setContent(request.getParameter("content"));

        return boardDto;
    }
}
