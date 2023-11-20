package dmu.dmuclub.servlet.board;

import dmu.dmuclub.dto.board.ViewBoardResponse;
import dmu.dmuclub.exception.board.BoardNotFoundException;
import dmu.dmuclub.service.board.BoardService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ViewBoardResponse> boardResponses = boardService.viewBoardAll();
            JSONArray jsonArray = new JSONArray();

            for (ViewBoardResponse boardResponse : boardResponses) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", boardResponse.getId());
                jsonObject.put("title", boardResponse.getTitle());
                jsonObject.put("content", boardResponse.getContent());
                jsonObject.put("author", boardResponse.getAuthor());
                jsonObject.put("createDate", boardResponse.getCreateDate());

                jsonArray.add(jsonObject);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonArray.toJSONString());
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
