package dmu.dmuclub.dao.board;

import dmu.dmuclub.dto.board.CreateBoardRequest;
import dmu.dmuclub.dto.board.ViewBoardResponse;

import java.sql.SQLException;
import java.util.List;

public interface BoardDao {
    void save(CreateBoardRequest boardDto) throws SQLException;
    List<ViewBoardResponse> findAll() throws SQLException;
    ViewBoardResponse findById(String boardId) throws SQLException;
}
