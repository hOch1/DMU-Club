package dmu.dmuclub.dao.friend.impl;

import dmu.dmuclub.dao.friend.FriendDao;
import dmu.dmuclub.dto.board.CreateBoardRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
