package dmu.dmuclub.servlet.match;

import dmu.dmuclub.service.member.MemberService;

import javax.servlet.annotation.WebServlet;

@WebServlet("/match")
public class MatchServlet {

    private final MemberService memberService = new MemberService();
}
