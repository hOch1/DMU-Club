package dmu.dmuclub.service.sign;

import dmu.dmuclub.dao.member.impl.MemberDaoImpl;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.dto.sign.SignInRequest;
import dmu.dmuclub.dto.sign.SignUpRequest;
import dmu.dmuclub.exception.member.MemberNotFoundException;
import dmu.dmuclub.exception.sign.EmailAlreadyExistsException;
import dmu.dmuclub.exception.sign.LoginFailureException;
import dmu.dmuclub.exception.sign.NicknameAlreadyExistsException;
import dmu.dmuclub.exception.sign.PhoneAlreadyExistsException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


public class SignService {

    private MemberDaoImpl memberDao = new MemberDaoImpl();


    public void signUp(SignUpRequest signUpRequest) {
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
            aleadyLoginValidation(session);
            signInValidate(memberDto, signInRequest);

            session.setAttribute("member", memberDto);
        } catch (LoginFailureException | MemberNotFoundException | PhoneAlreadyExistsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void existsValidate(SignUpRequest request) throws SQLException, ClassNotFoundException {
        if (memberDao.existsByEmail(request.getEmail()))
            throw new EmailAlreadyExistsException("이메일이 이미 사용중입니다");
        if (memberDao.existsByNickname(request.getNickname()))
            throw new NicknameAlreadyExistsException("닉네임이 이미 사용중입니다.");
        if (memberDao.existsByPhone(request.getPhone()))
            throw new PhoneAlreadyExistsException("핸드폰 번호가 이미 사용중입니다.");
    }

    private void signInValidate(MemberDto memberDto, SignInRequest signInRequest){
        if (memberDto == null)
            throw new MemberNotFoundException("가입한 회원정보가 없습니다");

        if (!memberDto.getPassword().equals(signInRequest.getPassword()) )
            throw new LoginFailureException("비밀번호를 확인해 주세요");
    }

    private void aleadyLoginValidation(HttpSession session){
        if (session.getAttribute("member") != null)
            throw new LoginFailureException("이미 로그인 되어있습니다");
    }
}
