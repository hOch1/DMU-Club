package dmu.dmuclub.dao.board.impl;

import dmu.dmuclub.dao.board.BoardDao;
import dmu.dmuclub.dto.board.CreateBoardRequest;
import dmu.dmuclub.dto.board.ViewBoardResponse;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dmu.dmuclub.common.JDBCTemplate.*;

@NoArgsConstructor
public class BoardDaoImpl implements BoardDao {

    private static final Connection CON = getConnection();

    public void save(CreateBoardRequest boardDto) throws SQLException {

        String query = "INSERT INTO board (title, content, member_id) VALUES(?,?,?)";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setString(1, boardDto.getTitle());
            preparedStatement.setString(2, boardDto.getContent());
            preparedStatement.setInt(3, boardDto.getMember_id());

            preparedStatement.executeUpdate();
        }
    }

    public List<ViewBoardResponse> findAll() throws SQLException {
        String query = "SELECT * FROM board";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<ViewBoardResponse> boardResponses = new ArrayList<>();

            while (resultSet.next()) {
                ViewBoardResponse boardResponse = new ViewBoardResponse();
                resultSetToBoardResponse(resultSet, boardResponse);
                boardResponses.add(boardResponse);
            }
            return boardResponses;
        }
    }

    public ViewBoardResponse findById(String boardId) throws SQLException {
        String query = "SELECT * FROM board WHERE id = ?";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setString(1, boardId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ViewBoardResponse boardResponse = new ViewBoardResponse();
                    resultSetToBoardResponse(resultSet, boardResponse);
                    return boardResponse;
                }
            }
        }
        return null;
    }


    @Override
    public void deleteById(String id) throws SQLException {
        String query = "DELETE FROM board WHERE id = ?";
        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();
        }
    }

    private void resultSetToBoardResponse(ResultSet resultSet, ViewBoardResponse boardResponse) throws SQLException {
        boardResponse.setId(resultSet.getInt("id"));
        boardResponse.setTitle(resultSet.getString("title"));
        boardResponse.setContent(resultSet.getString("content"));
        boardResponse.setCreateDate(resultSet.getString("createDate"));
        boardResponse.setAuthor(resultSet.getString("member_id"));
    }
}

