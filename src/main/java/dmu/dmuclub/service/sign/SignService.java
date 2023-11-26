package dmu.dmuclub.service.sign;

import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.dto.sign.SignInRequest;
import dmu.dmuclub.dto.sign.SignUpResquest;
import dmu.dmuclub.exception.member.MemberNotFoundException;
import dmu.dmuclub.exception.sign.EmailAlreadyExistsException;
import dmu.dmuclub.exception.sign.LoginFailureException;
import dmu.dmuclub.exception.sign.NicknameAlreadyExistsException;
import dmu.dmuclub.exception.sign.PhoneAlreadyExistsException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


public class SignService {

    private MemberDaoImpl memberDao = new MemberDaoImpl();


    public void signUp(SignUpResquest signUpRequest) {
        try {
            existsValidate(signUpRequest);

            memberDao.save(signUpRequest);
        } catch (EmailAlreadyExistsException | NicknameAlreadyExistsException | PhoneAlreadyExistsException |
                 SQLException | ClassNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void signIn(SignInRequest signInRequest, HttpSession session) throws SQLException {
        try {
            MemberDto memberDto = memberDao.findByEmail(signInRequest.getEmail());

            signInValidate(memberDto, signInRequest);

            session.setAttribute("member", memberDto);
        } catch (LoginFailureException | MemberNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void existsValidate(SignUpResquest resquest) throws SQLException, ClassNotFoundException {
        if (memberDao.existsByEmail(resquest.getEmail()))
            throw new EmailAlreadyExistsException("이메일이 이미 사용중입니다");
        if (memberDao.existsByNickname(resquest.getNickname()))
            throw new NicknameAlreadyExistsException("닉네임이 이미 사용중입니다.");
        if (memberDao.existsByPhone(resquest.getPhone()))
            throw new NicknameAlreadyExistsException("핸드폰 번호가 이미 사용중입니다.");
    }

    private void signInValidate(MemberDto memberDto, SignInRequest signInRequest){
        if (memberDto == null)
            throw new MemberNotFoundException("가입한 회원정보가 없습니다");

        if (!memberDto.getPassword().equals(signInRequest.getPassword()) )
            throw new LoginFailureException("비밀번호를 확인해 주세요");
    }
}
