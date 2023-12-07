package dmu.dmuclub.dao.chat.impl;

import dmu.dmuclub.dao.chat.ChatDao;
import dmu.dmuclub.dto.chat.ChatDto;
import dmu.dmuclub.dto.member.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dmu.dmuclub.common.JDBCTemplate.getConnection;

public class ChatDaoImpl implements ChatDao {
    private static final Connection CON = getConnection();
    @Override
    public void save(int from_member_id, int to_member_id) throws SQLException {

        String query = "INSERT INTO chat (from_member , to_member) VALUES(?,?)";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, from_member_id);
            preparedStatement.setInt(2, to_member_id);
            preparedStatement.executeUpdate();
        }
        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, to_member_id);
            preparedStatement.setInt(2, from_member_id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ChatDto findByFrom_member_id(int from_member_id) throws SQLException {
        String query = "SELECT * FROM chat WHERE from_member = ?";
        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, from_member_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ChatDto chatDto = new ChatDto();
                    resultSetToChatDto(resultSet, chatDto);
                    return chatDto;
                }
            }
        }
        return null;
    }


    @Override
    public List<ChatDto> findChat(int from_member_id) throws SQLException {
        String query = "SELECT * FROM chat WHERE from_member = ? OR to_member = ?";
        List<ChatDto> chatDtoList = new ArrayList<>();

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, from_member_id);
            preparedStatement.setInt(2, from_member_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ChatDto chatDto = new ChatDto();
                    resultSetToChatDto(resultSet, chatDto);
                    chatDtoList.add(chatDto);
                }
            }
        }
        return chatDtoList;
    }


    @Override
    public List<ChatDto> findChatList(int from_member_id) throws SQLException {
        List<ChatDto> chatDtoList = new ArrayList<>();
        String query = "SELECT * FROM chat WHERE from_member = ?";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, from_member_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ChatDto chatDto = new ChatDto();
                    resultSetToChatDto(resultSet, chatDto);
                    chatDtoList.add(chatDto);
                }
            }
        }
        return chatDtoList;
    }

    private void resultSetToChatDto(ResultSet resultSet, ChatDto chatDto) throws SQLException {
        chatDto.setId(resultSet.getInt("id"));
        chatDto.setFrom_member(resultSet.getInt("from_member"));
        chatDto.setTo_member(resultSet.getInt("to_member"));
    }
}
