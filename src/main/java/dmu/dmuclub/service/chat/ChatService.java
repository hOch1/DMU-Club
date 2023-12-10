package dmu.dmuclub.service.chat;

import dmu.dmuclub.dao.chat.ChatDao;
import dmu.dmuclub.dao.chat.ChatLogDao;
import dmu.dmuclub.dao.chat.impl.ChatDaoImpl;
import dmu.dmuclub.dao.chat.impl.ChatLogDaoImpl;
import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.chat.ChatDto;
import dmu.dmuclub.dto.chat.ChatLogDto;
import dmu.dmuclub.dto.member.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatService {

    private final ChatDao chatDao = new ChatDaoImpl();
    private final ChatLogDao chatLogDao = new ChatLogDaoImpl();
    private final MemberDao memberDao = new MemberDaoImpl();

    public void WriteChatLog(String message, MemberDto sendMember) throws SQLException {
        ChatDto chatDto = chatDao.findByFrom_member_id(sendMember.getId());
        chatLogDao.save(message, chatDto.getId());
    }

    public List<ChatLogDto> findChatLog(int memberId) throws SQLException {
        ChatDto chatDto = chatDao.findByFrom_member_id(memberId);
        List<ChatLogDto> chatLogDtoList = chatLogDao.findByChat_id(chatDto.getId());

        return chatLogDtoList;
    }

    public List<MemberDto> findChatList(int memberId) throws SQLException {
        List<ChatDto> chatDtoList = chatDao.findChatList(memberId);
        List<MemberDto> memberDtoList = new ArrayList<>();

        for (ChatDto chatDto : chatDtoList){
            MemberDto memberDto = memberDao.findById(String.valueOf(chatDto.getTo_member()));
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    public void createChat(int memberId) throws SQLException {

    }
}
