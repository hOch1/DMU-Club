package dmu.dmuclub.service.member;

import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.member.MemberDto;

import java.sql.SQLException;
import java.util.List;

public class MemberService {

    private final MemberDao memberDao = new MemberDaoImpl();


    public MemberDto findMember(String memberId) throws SQLException {

        MemberDto memberDto = memberDao.findById(memberId);


        return memberDto;
    }

    public List<MemberDto> findAll() throws SQLException {
        List<MemberDto> memberDtoList = memberDao.findAll();

        return memberDtoList;
    }
}
