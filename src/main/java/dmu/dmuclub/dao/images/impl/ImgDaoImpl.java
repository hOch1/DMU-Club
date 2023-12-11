package dmu.dmuclub.dao.images.impl;

import dmu.dmuclub.dao.images.ImgDao;
import dmu.dmuclub.dto.img.ImgDto;
import dmu.dmuclub.dto.member.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dmu.dmuclub.common.JDBCTemplate.getConnection;

public class ImgDaoImpl implements ImgDao {
    private static final Connection CON = getConnection();

    @Override
    public void save(int member_id, String fileName, String filePath) throws SQLException {
        String query = "INSERT INTO images (member_id, filename, filepath) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            preparedStatement.setInt(1, member_id);
            preparedStatement.setString(2, fileName);
            preparedStatement.setString(3, filePath);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ImgDto findByMember_id(int memberId) throws SQLException {
        ImgDto imgDto = new ImgDto();
        String query = "SELECT * FROM images WHERE member_id = ?";

        try(PreparedStatement preparedStatement = CON.prepareStatement(query)){
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    resultSetToImgDto(resultSet, imgDto);
                }
            }
        }
        return imgDto;
    }

    @Override
    public List<ImgDto> findAll() throws SQLException{
        List<ImgDto> imgDtoList = new ArrayList<>();
        String query = "SELECT * FROM images";

        try (PreparedStatement preparedStatement = CON.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ImgDto imgDto = new ImgDto();
                    resultSetToImgDto(resultSet, imgDto);
                    imgDtoList.add(imgDto);
                }
            }
        }
        return imgDtoList;
    }

    private void resultSetToImgDto(ResultSet resultSet, ImgDto imgDto) throws SQLException {
        imgDto.setMember_id(resultSet.getInt("member_id"));
        imgDto.setFilepath(resultSet.getString("filepath"));
        imgDto.setFilename(resultSet.getString("filename"));
    }
}
