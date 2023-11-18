package dmu.dmuclub.service.member;

import dmu.dmuclub.dao.member.MemberDao;

public class MemberService {

    private final MemberDao memberDao;

    public MemberService() {
        this.memberDao = new MemberDao();
    }
}
