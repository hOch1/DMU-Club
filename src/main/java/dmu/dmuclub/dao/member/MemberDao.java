package dmu.dmuclub.dao.member;

import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.dto.sign.SignUpResquest;

import java.sql.SQLException;

public interface MemberDao {

    void save(SignUpResquest signUpRequest) throws ClassNotFoundException, SQLException;
    MemberDto findByEmail(String email) throws SQLException;
    MemberDto findById(String id) throws SQLException;
}
