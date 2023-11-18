package dmu.dmuclub.dao.board;

import dmu.dmuclub.dto.board.BoardDto;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dmu.dmuclub.jdbc.JDBCTemplate.*;

@NoArgsConstructor
public class BoardDao {

    private static final Connection CON = getConnection();

    public void save(BoardDto boardDto) throws SQLException {

        String query = "INSERT INTO board (title, content, createDate, member_id) VALUES(?,?,?,?)";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setString(1, boardDto.getTitle());
            preparedStatement.setString(2, boardDto.getContent());
            preparedStatement.setString(3, boardDto.getCreateDate());
            preparedStatement.setInt(4, boardDto.getMember_id());

            preparedStatement.executeUpdate();
        }
    }
}

