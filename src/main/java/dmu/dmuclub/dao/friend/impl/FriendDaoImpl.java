package dmu.dmuclub.dao.friend.impl;

import dmu.dmuclub.dao.friend.FriendDao;
import dmu.dmuclub.dto.board.CreateBoardRequest;
import dmu.dmuclub.dto.friend.FriendDto;
import dmu.dmuclub.dto.member.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dmu.dmuclub.common.JDBCTemplate.getConnection;

public class FriendDaoImpl implements FriendDao {
    private static final Connection CON = getConnection();

    public void save(int member1_id, int member2_id) throws SQLException {

        String query = "INSERT INTO friend (member1_id , member2_id) VALUES(?,?)";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, member1_id);
            preparedStatement.setInt(2, member2_id);
            preparedStatement.executeUpdate();
        }
        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, member2_id);
            preparedStatement.setInt(2, member1_id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<FriendDto> findById(int member_id) throws SQLException{
        String query = "SELECT * FROM friend where member1_id = ?";
        List<FriendDto> friendDtoList = new ArrayList<>();

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, member_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    FriendDto friendDto = new FriendDto();
                    resultSetToFriendDto(resultSet, friendDto);
                    friendDtoList.add(friendDto);
                }
            }
        }
        return friendDtoList;
    }

    private void resultSetToFriendDto(ResultSet resultSet, FriendDto friendDto) throws SQLException {
        friendDto.setFriend(resultSet.getInt("member2_id"));
    }
}
