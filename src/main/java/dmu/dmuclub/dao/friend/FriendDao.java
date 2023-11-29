package dmu.dmuclub.dao.friend;

import java.sql.SQLException;

public interface FriendDao {
    void save(int member1_id, int member2_id) throws SQLException;
}
