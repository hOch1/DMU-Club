package dmu.dmuclub.dao.member;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.dto.sign.SignUpResquest;

import java.sql.SQLException;
import java.util.List;

public interface MemberDao {

    void save(SignUpResquest signUpRequest) throws ClassNotFoundException, SQLException;
    void deleteById(String id) throws SQLException;


    MemberDto findByEmail(String email) throws SQLException;
    MemberDto findById(String id) throws SQLException;
    List<MemberDto> findAll() throws SQLException;
}
