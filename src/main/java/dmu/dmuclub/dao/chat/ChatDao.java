package dmu.dmuclub.dao.chat;

import dmu.dmuclub.dto.chat.ChatDto;

import java.sql.SQLException;
import java.util.List;

public interface ChatDao {
    void save(int from_member_id, int to_member_id) throws SQLException;
    List<ChatDto> findChat(int from_member_id) throws SQLException;
}
