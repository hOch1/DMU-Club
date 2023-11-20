package dmu.dmuclub.dao.board;

import dmu.dmuclub.dto.board.CreateBoardRequest;
import dmu.dmuclub.dto.board.ViewBoardRequest;
import dmu.dmuclub.dto.board.ViewBoardResponse;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dmu.dmuclub.jdbc.JDBCTemplate.*;

@NoArgsConstructor
public class BoardDao {

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
            return boardResponses;
        }
    }

    public ViewBoardRequest findById(String boardId) throws SQLException {
        String query = "SELECT * FROM board WHERE id = ?";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setString(1, boardId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return ViewBoardRequest.builder()
                            .title(resultSet.getString("title"))
                            .content(resultSet.getString("content"))
                            .createDate(resultSet.getString("createDate"))
                            .memberId(resultSet.getString("member_id"))
                            .build();
                } else {
                    return null;
                }
            }
        }
    }
}

