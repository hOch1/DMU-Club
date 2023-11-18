package dmu.dmuclub.service.member;

import dmu.dmuclub.dao.member.impl.MemberDaoImpl;

public class MemberService {

    private final MemberDaoImpl memberDao;

    public MemberService() {
        this.memberDao = new MemberDaoImpl();
    }
}
