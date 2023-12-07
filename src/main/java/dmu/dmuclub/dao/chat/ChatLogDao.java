package dmu.dmuclub.dao.chat;

import dmu.dmuclub.dto.chat.ChatLogDto;

import java.sql.SQLException;
import java.util.List;

public interface ChatLogDao {

    void save(String message, int chat_id) throws SQLException;

    List<ChatLogDto> findByChat_id(int chat_id) throws SQLException;
}
