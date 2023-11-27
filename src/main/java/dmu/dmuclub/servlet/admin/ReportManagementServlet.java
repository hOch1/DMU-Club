package dmu.dmuclub.servlet.admin;

import dmu.dmuclub.dto.report.ReportResponse;
import dmu.dmuclub.service.report.ReportService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/report")
public class ReportManagementServlet extends HttpServlet {

    private final ReportService reportService = new ReportService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ReportResponse> reportResponses = reportService.findAll();

            request.setAttribute("reportList", reportResponses);
            // 임시 response
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/adminReport.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
