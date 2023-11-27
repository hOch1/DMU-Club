package dmu.dmuclub.dao.report;

import dmu.dmuclub.dto.report.ReportRequest;
import dmu.dmuclub.dto.report.ReportResponse;

import java.sql.SQLException;
import java.util.List;

public interface ReportDao {

    void save(ReportRequest reportRequest) throws SQLException;
    List<ReportResponse> findAll() throws SQLException;
}
