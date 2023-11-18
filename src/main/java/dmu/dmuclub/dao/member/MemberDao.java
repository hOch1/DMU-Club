package dmu.dmuclub.dao.member;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.dto.sign.SignUpResquest;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static dmu.dmuclub.jdbc.JDBCTemplate.*;

@NoArgsConstructor
public class MemberDao {

    private static final Connection CON = getConnection();

    public void save(SignUpResquest signUpRequest) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO member (email, password, username, nickname, phone, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {

            List<String> signUpRequestList = signUpRequest.toList(signUpRequest);
            for (int i = 0; i < signUpRequestList.size(); i++)
                preparedStatement.setString(i + 1, signUpRequestList.get(i));

            preparedStatement.executeUpdate();
        }
    }

    public MemberDto findByEmail(String email) throws SQLException {
        MemberDto memberDto = new MemberDto();

        String query = "SELECT * FROM member WHERE email = ?";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    memberDto.setEmail(resultSet.getString("email"));
                    memberDto.setPassword(resultSet.getString("password"));
                    memberDto.setNickname(resultSet.getString("nickname"));
                    memberDto.setPhone(resultSet.getString("phone"));
                    memberDto.setUsername(resultSet.getString("username"));
                    memberDto.setRole(resultSet.getString("role"));

                    return memberDto;
                }
                return null;
            }
        }
    }


    private boolean checkExists(String columnName, String value) throws SQLException {
        String query = "SELECT COUNT(*) FROM member WHERE " + columnName + " = ?";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setString(1, value);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }



    public boolean existsByEmail(String email) throws SQLException {
        return checkExists("email", email);
    }

    public boolean existsByNickname(String nickname) throws SQLException {
        return checkExists("nickname", nickname);
    }

    public boolean existsByPhone(String phone) throws SQLException {
        return checkExists("phone", phone);
    }
}
