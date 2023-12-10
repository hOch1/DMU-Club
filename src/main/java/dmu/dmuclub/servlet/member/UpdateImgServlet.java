package dmu.dmuclub.servlet.member;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.service.member.ProfileService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/member/updateImg")
@MultipartConfig
public class UpdateImgServlet extends HttpServlet {

    private final ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/info/updateImg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            HttpSession session = req.getSession();
            MemberDto memberDto = (MemberDto) session.getAttribute("member");
            List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            String webappPath = req.getServletContext().getRealPath("/");
            String filePath = webappPath+"img"+File.separator;

            profileService.updateImg(fileItems, filePath ,memberDto);

        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
