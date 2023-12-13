package dmu.dmuclub.dao.friend;

import dmu.dmuclub.dto.friend.AskFriendDto;

import java.sql.SQLException;
import java.util.List;

public interface AskFriendDao {
    void save(int from_member, int to_member) throws SQLException;
    void deleteByFrom_memberAndTo_member(int from_member, int to_member) throws SQLException;

    List<AskFriendDto> findByTo_member(int to_member) throws SQLException;
}
