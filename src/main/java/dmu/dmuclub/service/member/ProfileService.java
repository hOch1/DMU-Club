package dmu.dmuclub.service.member;

import dmu.dmuclub.dao.images.ImgDao;
import dmu.dmuclub.dao.images.impl.ImgDaoImpl;
import dmu.dmuclub.dto.img.ImgDto;
import dmu.dmuclub.dto.member.MemberDto;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class ProfileService {

    private final ImgDao imgDao = new ImgDaoImpl();

    public void updateImg(List<FileItem> fileItems, String filePath, MemberDto memberDto) throws Exception {
        for (FileItem item : fileItems){
            if (!item.isFormField()){
                String originalFileName = new File(item.getName()).getName();
                String fileExtension = getFileExtension(originalFileName);
                String newFileName = memberDto.getNickname()+"."+fileExtension;

                String newFilePath = filePath+newFileName;

                item.write(new File(newFilePath));

                imgDao.save(memberDto.getId(), newFileName, newFilePath);
            }
        }
    }

    public ImgDto findByMember_id(int member_Id) throws SQLException {
        ImgDto imgDto = imgDao.findByMember_id(member_Id);
        return imgDto;
    }

    public List<ImgDto> findAll() throws SQLException {
        List<ImgDto> imgDtoList = imgDao.findAll();



        return imgDtoList;
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }
}
