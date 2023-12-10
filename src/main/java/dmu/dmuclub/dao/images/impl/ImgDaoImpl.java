package dmu.dmuclub.dao.images.impl;

import dmu.dmuclub.dao.images.ImgDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dmu.dmuclub.common.JDBCTemplate.getConnection;

public class ImgDaoImpl implements ImgDao {
    private static final Connection CON = getConnection();

    @Override
    public void save(int member_id, String fileName, String filePath) throws SQLException {
        String sql = "INSERT INTO images (member_id, filename, filepath) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = CON.prepareStatement(sql)) {
            preparedStatement.setInt(1, member_id);
            preparedStatement.setString(2, fileName);
            preparedStatement.setString(3, filePath);

            preparedStatement.executeUpdate();
        }
    }
}
