package dmu.dmuclub.dao.images;

import dmu.dmuclub.dto.img.ImgDto;

import java.sql.SQLException;
import java.util.List;

public interface ImgDao {

    void save(int member_id, String fileName, String filePath) throws SQLException;

    ImgDto findByMember_id(int memberId) throws SQLException;
    List<ImgDto> findAll() throws SQLException;
}
