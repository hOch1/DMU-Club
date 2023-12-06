package dmu.dmuclub.service.chat;

import dmu.dmuclub.dao.chat.ChatDao;
import dmu.dmuclub.dao.chat.impl.ChatDaoImpl;
import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.chat.ChatDto;
import dmu.dmuclub.dto.member.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatService {

    private final ChatDao chatDao = new ChatDaoImpl();
    private final MemberDao memberDao = new MemberDaoImpl();

    public List<MemberDto> findChat(int memberId) throws SQLException {
        List<ChatDto> chatDtoList = chatDao.findChat(memberId);
        List<MemberDto> memberDtoList = new ArrayList<>();

        for (ChatDto chatDto : chatDtoList){
            MemberDto memberDto = memberDao.findById(String.valueOf(chatDto.getTo_member()));
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }
}
