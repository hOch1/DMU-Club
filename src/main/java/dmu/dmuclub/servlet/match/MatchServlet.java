package dmu.dmuclub.servlet.match;

import dmu.dmuclub.dto.img.ImgDto;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.member.MemberService;
import dmu.dmuclub.service.member.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();
    private final ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            MemberDto memberDto = (MemberDto) session.getAttribute("member");
            List<MemberDto> memberDtoList = memberService.findAll();

            request.setAttribute("memberList", changeList(memberDtoList, memberDto));
            request.getRequestDispatcher("/matching/matching.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<MemberDto> changeList(List<MemberDto> memberDtoList, MemberDto nowMember){
        List<MemberDto> matchList = new ArrayList<>();

        for (MemberDto memberDto : memberDtoList){
            if (!(memberDto.getId() == nowMember.getId()) && !(memberDto.getRole().equals("ADMIN")))
                matchList.add(memberDto);
        }
        return matchList;
    }
}
