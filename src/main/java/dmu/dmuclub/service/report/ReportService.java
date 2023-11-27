package dmu.dmuclub.service.report;

import dmu.dmuclub.dao.report.ReportDao;
import dmu.dmuclub.dao.report.impl.ReportDaoImpl;
import dmu.dmuclub.dto.report.ReportResponse;

import java.sql.SQLException;
import java.util.List;

public class ReportService {

    private final ReportDaoImpl reportDao = new ReportDaoImpl();

    public List<ReportResponse> findAll() throws SQLException {

        List<ReportResponse> reportResponses = reportDao.findAll();

        return reportResponses;
    }
}
