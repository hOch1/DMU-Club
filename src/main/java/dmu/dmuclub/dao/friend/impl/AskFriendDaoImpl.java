package dmu.dmuclub.dao.friend.impl;

import dmu.dmuclub.dao.friend.AskFriendDao;
import dmu.dmuclub.dto.friend.AskFriendDto;
import dmu.dmuclub.dto.friend.FriendDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dmu.dmuclub.common.JDBCTemplate.getConnection;

public class AskFriendDaoImpl implements AskFriendDao {
    private static final Connection CON = getConnection();

    @Override
    public void save(int from_member, int to_member) throws SQLException {
        String query = "INSERT INTO askfriend (from_member , to_member) VALUES(?,?)";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, from_member);
            preparedStatement.setInt(2, to_member);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<AskFriendDto> findByTo_member(int to_member) throws SQLException {
        String query = "SELECT * FROM askfriend WHERE to_member = ?";
        List<AskFriendDto> askFriendDtoList = new ArrayList<>();

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, to_member);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    AskFriendDto askFriendDto = new AskFriendDto();
                    resultSetToAskFriendDto(resultSet, askFriendDto);
                    askFriendDtoList.add(askFriendDto);
                }
            }
        }
        return askFriendDtoList;
    }

    @Override
    public void deleteByFrom_memberAndTo_member(int from_member, int to_member) throws SQLException {
        String query = "DELETE FROM askFriend WHERE from_member = ? AND to_member = ?";
        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, from_member);
            preparedStatement.setInt(2, to_member);
            preparedStatement.executeUpdate();
        }    }

    private void resultSetToAskFriendDto(ResultSet resultSet, AskFriendDto askFriendDto) throws SQLException {
        askFriendDto.setId(resultSet.getInt("id"));
        askFriendDto.setTo_member(resultSet.getInt("to_member"));
        askFriendDto.setFrom_member(resultSet.getInt("from_member"));
    }
}
