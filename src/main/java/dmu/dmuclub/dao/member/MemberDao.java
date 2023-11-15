package dmu.dmuclub.dao.member;

import dmu.dmuclub.dto.sign.SignUpResquest;
import dmu.dmuclub.exception.sign.EmailAlreadyExistsException;
import dmu.dmuclub.exception.sign.NicknameAlreadyExistsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/DMUCLUB";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public void save(SignUpResquest signUpRequest) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO member (email, password, username, nickname, phone, role) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, signUpRequest.getEmail());
                preparedStatement.setString(2, signUpRequest.getPassword());
                preparedStatement.setString(3, signUpRequest.getUsername());
                preparedStatement.setString(4, signUpRequest.getNickname());
                preparedStatement.setString(5, signUpRequest.getPhone());
                preparedStatement.setString(6, String.valueOf(Role.NORMAL));

                preparedStatement.executeUpdate();

            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    private boolean checkExists(String columnName, String value) throws SQLException, ClassNotFoundException {
        try (Connection connection = getConnection()) {
            String query = "SELECT COUNT(*) FROM member WHERE " + columnName + " = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, value);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next() && resultSet.getInt(1) > 0;
                } finally {
                    connection.close();
                    preparedStatement.close();
                }
            }
        }
    }

    public boolean existsByEmail(String email) throws SQLException, ClassNotFoundException {
        return checkExists("email", email);
    }

    public boolean existsByNickname(String nickname) throws SQLException, ClassNotFoundException {
        return checkExists("nickname", nickname);
    }

    public boolean existsByPhone(String phone) throws SQLException, ClassNotFoundException {
        return checkExists("phone", phone);
    }
}
