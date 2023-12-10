package dmu.dmuclub.service.friend;

import dmu.dmuclub.dao.friend.FriendDao;
import dmu.dmuclub.dao.friend.impl.FriendDaoImpl;
import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.friend.FriendDto;
import dmu.dmuclub.dto.member.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendService {

    private final FriendDao friendDao = new FriendDaoImpl();
    private final MemberDao memberDao = new MemberDaoImpl();

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
