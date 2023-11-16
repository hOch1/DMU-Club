package dmu.dmuclub.dao.member;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.dto.sign.SignUpResquest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dmu.dmuclub.jdbc.JDBCTemplate.*;

public class MemberDao {

    public MemberDao() {
    }


    public void save(SignUpResquest signUpRequest) throws ClassNotFoundException, SQLException{
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
        }

    }

    public MemberDto findByEmail(String email) throws SQLException {
        MemberDto memberDto = new MemberDto();

        try (Connection connection = getConnection()){
            String query = "SELECT * FROM member WHERE email = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()){
                        memberDto.setEmail(resultSet.getString("email"));
                        memberDto.setPassword(resultSet.getString("password"));
                        memberDto.setNickname(resultSet.getString("nickname"));
                        memberDto.setPhone(resultSet.getString("phone"));
                        memberDto.setUsername(resultSet.getString("username"));
                        memberDto.setRole(resultSet.getString("role"));

                        return memberDto;
                    }
                }
            }
        }

        return null;
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
