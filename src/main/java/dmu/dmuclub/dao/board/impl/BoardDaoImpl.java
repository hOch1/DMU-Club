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

            close(preparedStatement, CON);
        }
    }

    public List<ViewBoardResponse> findAll() throws SQLException {
        String query = "SELECT * FROM board";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()){

            List<ViewBoardResponse> boardResponses = new ArrayList<>();

            while(resultSet.next()){
                ViewBoardResponse  boardResponse = ViewBoardResponse.builder()
                        .title(resultSet.getString("title"))
                        .content(resultSet.getString("content"))
                        .createDate(resultSet.getString("createDate"))
                        .author(resultSet.getString("member_id"))
                        .build();

                boardResponses.add(boardResponse);
            }
            close(preparedStatement, CON);
            return boardResponses;
        }
    }

    public ViewBoardResponse findById(String boardId) throws SQLException {
        String query = "SELECT * FROM board WHERE id = ?";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setString(1, boardId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    close(preparedStatement, CON);
                    return ViewBoardResponse.builder()
                            .id(resultSet.getInt("id"))
                            .title(resultSet.getString("title"))
                            .content(resultSet.getString("content"))
                            .createDate(resultSet.getString("createDate"))
                            .author(resultSet.getString("member_id"))
                            .build();
                } else {
                    close(preparedStatement, CON);
                    return null;
                }
            }

        }
    }
}

