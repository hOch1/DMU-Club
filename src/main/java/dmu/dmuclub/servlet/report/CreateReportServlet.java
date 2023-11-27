package dmu.dmuclub.servlet.report;

import dmu.dmuclub.dto.report.ReportRequest;
import dmu.dmuclub.dto.sign.SignInRequest;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            reportService.createReport(createReportRequest(request), session);

            // 임시 Response
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("게시물 생성 완료");
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
