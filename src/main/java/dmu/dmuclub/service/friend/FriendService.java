package dmu.dmuclub.service.friend;

import dmu.dmuclub.dao.friend.AskFriendDao;
import dmu.dmuclub.dao.friend.FriendDao;
import dmu.dmuclub.dao.friend.impl.AskFriendDaoImpl;
import dmu.dmuclub.dao.friend.impl.FriendDaoImpl;
import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.friend.AskFriendDto;
import dmu.dmuclub.dto.friend.FriendDto;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.chat.ChatService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendService {

    private final FriendDao friendDao = new FriendDaoImpl();
    private final AskFriendDao askFriendDao = new AskFriendDaoImpl();
    private final MemberDao memberDao = new MemberDaoImpl();
    private final ChatService chatService = new ChatService();

    public void addFriend(int member1_id, int member2_id) throws SQLException {
        friendDao.save(member1_id, member2_id);
        chatService.createChat(member1_id, member2_id);
    }

    public void addAskFriend(int from_member, int to_member) throws SQLException {
        askFriendDao.save(from_member, to_member);
    }

    public void acceptFriend(int from_member, int to_member) throws SQLException {
        askFriendDao.deleteByFrom_memberAndTo_member(from_member, to_member);
        addFriend(from_member, to_member);
    }

    public void refuseFried(int from_member, int to_member) throws SQLException {
        askFriendDao.deleteByFrom_memberAndTo_member(from_member, to_member);
    }

    public List<MemberDto> findAskFriend(int to_member) throws SQLException {
        List<AskFriendDto> askFriendDtoList = askFriendDao.findByTo_member(to_member);
        List<MemberDto> memberDtoList = new ArrayList<>();

        for (AskFriendDto askFriendDto : askFriendDtoList){
            MemberDto memberDto = memberDao.findById(String.valueOf(askFriendDto.getFrom_member()));
            memberDtoList.add(memberDto);
        }

        return memberDtoList;
    }

    public List<MemberDto> findFriends(int member_id) throws SQLException {
        List<FriendDto> friendDtoList = friendDao.findById(member_id);
        List<MemberDto> memberDtoList = new ArrayList<>();

        for (FriendDto friendDto : friendDtoList){
            MemberDto memberDto = memberDao.findById(String.valueOf(friendDto.getFriend()));
            memberDtoList.add(memberDto);
        }
        return  memberDtoList;
    }
}
