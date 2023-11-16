package dmu.dmuclub.dao.board;

import dmu.dmuclub.dto.board.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDao {

    private static final String URL = "jdbc:mysql://localhost:3306/DMUCLUB";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void save(BoardDto boardDto) throws SQLException, ClassNotFoundException {
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
