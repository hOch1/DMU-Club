package dmu.dmuclub.dao.chat.impl;

import dmu.dmuclub.dao.chat.ChatLogDao;
import dmu.dmuclub.dto.chat.ChatDto;
import dmu.dmuclub.dto.chat.ChatLogDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dmu.dmuclub.common.JDBCTemplate.getConnection;

public class ChatLogDaoImpl implements ChatLogDao {
    private static final Connection CON = getConnection();


    @Override
    public void save(String message, int chat_id) throws SQLException {
        String query = "INSERT INTO chatlog (id , message) VALUES(?,?)";
        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, chat_id);
            preparedStatement.setString(2, message);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<ChatLogDto> findByChat_id(int chat_id) throws SQLException {
        List<ChatLogDto> chatDtoList = new ArrayList<>();
        String query = "SELECT * FROM chatlog WHERE id = ? ORDER BY sendTime asc";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, chat_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ChatLogDto chatDto = new ChatLogDto();
                    resultSetToChatLogDto(resultSet, chatDto);
                    chatDtoList.add(chatDto);
                }
            }
        }
        return chatDtoList;
    }

    private void resultSetToChatLogDto(ResultSet resultSet, ChatLogDto chatDto) throws SQLException {
        chatDto.setId(resultSet.getInt("id"));
        chatDto.setMessage(resultSet.getString("message"));
        chatDto.setSendTime(resultSet.getTimestamp("sendTime").toLocalDateTime());
    }
}
