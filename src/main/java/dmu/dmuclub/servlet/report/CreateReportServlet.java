package dmu.dmuclub.servlet.report;

import dmu.dmuclub.dto.report.ReportRequest;
import dmu.dmuclub.service.report.ReportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/report/create")
public class CreateReportServlet extends HttpServlet {

    private final ReportService reportService = new ReportService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            reportService.createReport(createReportRequest(request), session);


            response.sendRedirect("/main");
        }catch (RuntimeException | SQLException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    private ReportRequest createReportRequest(HttpServletRequest request) {
        return ReportRequest.builder()
                .title(request.getParameter("title"))
                .content(request.getParameter("content"))
                .build();

    }
}
