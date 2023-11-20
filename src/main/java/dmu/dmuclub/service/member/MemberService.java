package dmu.dmuclub.service.member;

import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.member.MemberDto;

public class MemberService {

    private final MemberDao memberDao;

    public MemberService() {
        this.memberDao = new MemberDaoImpl();
    }

    public MemberDto findMember(int memberId){
        return new MemberDto();
    }
}
