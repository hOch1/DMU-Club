package dmu.dmuclub.service.member;

import dmu.dmuclub.dao.member.MemberDao;

public class MemberService {

    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
