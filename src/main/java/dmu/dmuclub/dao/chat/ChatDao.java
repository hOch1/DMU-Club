package dmu.dmuclub.dao.chat;

import dmu.dmuclub.dto.chat.ChatDto;

import java.sql.SQLException;
import java.util.List;

public interface ChatDao {
    void save(int from_member_id, int to_member_id) throws SQLException;
    void deleteByFrom_Member_IdAndTo_Member_Id(int from_member_id, int to_member_id) throws SQLException;


    ChatDto findByFrom_member_id(int from_member_id) throws SQLException;
    ChatDto findByFrom_member_idAndTo_member_id(int from_member_id, int to_member_id) throws SQLException;
    List<ChatDto> findChat(int from_member_id) throws SQLException;
    List<ChatDto> findChatList(int from_member_id) throws SQLException;
}
