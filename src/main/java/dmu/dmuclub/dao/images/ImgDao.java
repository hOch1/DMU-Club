package dmu.dmuclub.dao.images;

import java.sql.SQLException;

public interface ImgDao {

    void save(int member_id, String fileName, String filePath) throws SQLException;
}
