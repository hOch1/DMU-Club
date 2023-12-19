package dmu.dmuclub.dao.friend;

import dmu.dmuclub.dto.friend.FriendDto;

import java.sql.SQLException;
import java.util.List;

public interface FriendDao {
    void save(int member1_id, int member2_id) throws SQLException;
    void deleteByMember1_idAndMember2_id(int member1_id, int member2_id) throws SQLException;

    List<FriendDto> findById(int member_id) throws SQLException;
}
