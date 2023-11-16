package dmu.dmuclub.dao.board;

import dmu.dmuclub.dto.board.BoardDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dmu.dmuclub.jdbc.JDBCTemplate.*;

public class BoardDao {

    public BoardDao() {
    }

    public void save(BoardDto boardDto) throws SQLException {
        try (Connection connection = getConnection()){
            String query = "INSERT INTO board (title, content, createDate, member_id) VALUES(?,?,?,?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, boardDto.getTitle());
                preparedStatement.setString(2, boardDto.getContent());
                preparedStatement.setString(3, boardDto.getCreateDate());
                preparedStatement.setInt(4, boardDto.getMember_id());

                preparedStatement.executeUpdate();
            } finally {
                connection.close();
            }
        }
    }
}
