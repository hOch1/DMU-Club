package dmu.dmuclub.service.member;

import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.img.ImgDto;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.exception.member.MemberNotFoundException;

import java.sql.SQLException;
import java.util.List;

public class MemberService {

    private final MemberDao memberDao = new MemberDaoImpl();
    private final ProfileService profileService = new ProfileService();


    public MemberDto findMember(String memberId) throws SQLException {
        MemberDto memberDto = memberDao.findById(memberId);

        return memberDto;
    }

    public MemberDto findMember_nickname(String nickname) throws SQLException {
        return memberDao.findByNickname(nickname);
    }

    public List<MemberDto> findAll() throws SQLException {
        List<MemberDto> memberDtoList = memberDao.findAll();
        List<ImgDto> imgDtoList = profileService.findAll();

        for (MemberDto memberDto : memberDtoList){
            for (ImgDto imgDto : imgDtoList){
                if (memberDto.getId() == imgDto.getMember_id())
                    memberDto.setFilename(imgDto.getFilename());
            }
        }

        return memberDtoList;
    }

    public List<MemberDto> matchMember(String mbti) throws SQLException {
        List<MemberDto> memberDtoList = memberDao.findByMBTI(matchMBTI(mbti));

        if (memberDtoList.isEmpty())
            throw new MemberNotFoundException("회원들이 없습니다");

        return memberDtoList;
    }

    public void deleteMember(String member_id) throws SQLException {
        memberDao.deleteById(member_id);
    }


    private String matchMBTI(String mbti){
        if (mbti.equals("INFP"))
            return "ENFJ";
        else if (mbti.equals("ENFP"))
            return "INFJ";
        else if (mbti.equals("INFJ"))
            return "ENFP";
        else if (mbti.equals("ENFJ"))
            return "ISFP";
        else if (mbti.equals("INTJ"))
            return "ENTP";
        else if (mbti.equals("INTP"))
            return "ESTJ";
        else if (mbti.equals("ENTP"))
            return "INTJ";
        else if (mbti.equals("ISFP"))
            return "ESFJ";
        else if (mbti.equals("ESFP"))
            return "ISTJ";
        else if (mbti.equals("ISTP"))
            return "ESFJ";
        else if (mbti.equals("ESTP"))
            return "ISFJ";
        else if (mbti.equals("ESFJ"))
            return "ISFP";
        else if (mbti.equals("ISTJ"))
            return "ESFP";
        else if (mbti.equals("ESTJ"))
            return "ISTP";
        else
            return "";
    }
}
