package dmu.dmuclub.dao.member.impl;

import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.dto.sign.SignUpRequest;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dmu.dmuclub.common.JDBCTemplate.*;

@NoArgsConstructor
public class MemberDaoImpl implements MemberDao {

    private static final Connection CON = getConnection();

    public void save(SignUpRequest signUpRequest) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO member (email, password, username, nickname, phone, mbti, hobby, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
                    resultSetToMemberDto(resultSet, memberDto);
                    return memberDto;
                }
                return null;
            }
        }
    }



    public MemberDto findById(String id) throws SQLException {
        MemberDto memberDto = new MemberDto();
        String query = "SELECT * FROM member WHERE id = ?";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    resultSetToMemberDto(resultSet, memberDto);
                    return memberDto;
                }
                return null;
            }
        }
    }

    @Override
    public MemberDto findByNickname(String nickname) throws SQLException {
        MemberDto memberDto = new MemberDto();
        String query = "SELECT * FROM member WHERE nickname = ?";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setString(1, nickname);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    resultSetToMemberDto(resultSet, memberDto);
                    return memberDto;
                }
                return null;
            }
        }
    }

    @Override
    public List<MemberDto> findAll() throws SQLException{
        List<MemberDto> memberDtoList = new ArrayList<>();
        String query = "select * from member";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MemberDto memberDto = new MemberDto();
                    resultSetToMemberDto(resultSet, memberDto);
                    memberDtoList.add(memberDto);
                }
            }
        }
        return memberDtoList;
    }

    @Override
    public List<MemberDto> findByMBTI(String mbti) throws SQLException {
        List<MemberDto> memberDtoList = new ArrayList<>();
        String query = "SELECT * FROM member WHERE mbti = ?";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setString(1, mbti);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MemberDto memberDto = new MemberDto();
                    resultSetToMemberDto(resultSet, memberDto);
                    memberDtoList.add(memberDto);
                }
            }
        }
        return memberDtoList;
    }

    public void deleteById(String id) throws SQLException{
        String query = "DELETE FROM member WHERE id = ?";
        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();
        }
    }



    private void resultSetToMemberDto(ResultSet resultSet, MemberDto memberDto) throws SQLException {
        memberDto.setId(resultSet.getInt("id"));
        memberDto.setEmail(resultSet.getString("email"));
        memberDto.setPassword(resultSet.getString("password"));
        memberDto.setNickname(resultSet.getString("nickname"));
        memberDto.setPhone(resultSet.getString("phone"));
        memberDto.setUsername(resultSet.getString("username"));
        memberDto.setMbti(resultSet.getString("mbti"));
        memberDto.setRole(resultSet.getString("role"));
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
