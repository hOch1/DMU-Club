package dmu.dmuclub.service.report;

import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dao.report.ReportDao;
import dmu.dmuclub.dao.report.impl.ReportDaoImpl;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.dto.report.ReportRequest;
import dmu.dmuclub.dto.report.ReportResponse;
import dmu.dmuclub.exception.member.HasNotRoleException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class ReportService {

    private final ReportDao reportDao = new ReportDaoImpl();
    private final MemberDao memberDao = new MemberDaoImpl();

    public void createReport(ReportRequest reportRequest, HttpSession session) throws SQLException{
        try {
            String email = isLoginValidate((MemberDto) session.getAttribute("member"));
            MemberDto memberDto = memberDao.findByEmail(email);

            reportRequest.setMember_id(memberDto.getId());

            reportDao.save(reportRequest);
        }catch (HasNotRoleException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<ReportResponse> findAll() throws SQLException {

        List<ReportResponse> reportResponses = reportDao.findAll();

        return reportResponses;
    }

    private String isLoginValidate(MemberDto memberDto){
        if (memberDto == null)
            throw new HasNotRoleException("권한이 없습니다");

        return memberDto.getEmail();
    }
}
