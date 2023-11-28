package dmu.dmuclub.dao.report.impl;

import dmu.dmuclub.dao.report.ReportDao;
import dmu.dmuclub.dto.report.ReportRequest;
import dmu.dmuclub.dto.report.ReportResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dmu.dmuclub.common.JDBCTemplate.getConnection;

public class ReportDaoImpl implements ReportDao {

    private static final Connection CON = getConnection();

    @Override
    public void save(ReportRequest reportRequest) throws SQLException {
        String query = "INSERT INTO member (title, content, member_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setString(1, reportRequest.getTitle());
            preparedStatement.setString(2, reportRequest.getContent());
            preparedStatement.setInt(3, reportRequest.getMember_id());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<ReportResponse> findAll() throws SQLException {
        String query = "SELECT * FROM report";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<ReportResponse> reportResponses = new ArrayList<>();

            while (resultSet.next()) {
                ReportResponse response = ReportResponse.builder()
                        .title(resultSet.getString("title"))
                        .content(resultSet.getString("content"))
                        .solve(resultSet.getBoolean("solve"))
                        .author(resultSet.getString("member_id"))
                        .build();

                reportResponses.add(response);
            }
            return reportResponses;
        }
    }
}
