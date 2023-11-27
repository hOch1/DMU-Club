package dmu.dmuclub.service.member;

import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.exception.member.MemberNotFoundException;

import java.sql.SQLException;

public class MemberService {

    private final MemberDao memberDao;

    public MemberService() {
        this.memberDao = new MemberDaoImpl();
    }

    public MemberDto findMember(String memberId) throws SQLException {

        MemberDto memberDto = memberDao.findById(memberId);


        return memberDto;
    }
}
