package dmu.dmuclub.service.sign;

import dmu.dmuclub.dao.member.MemberDao;
import dmu.dmuclub.dto.member.MemberDto;
import dmu.dmuclub.dto.sign.SignInRequest;
import dmu.dmuclub.dto.Response;
import dmu.dmuclub.dto.sign.SignUpResquest;
import dmu.dmuclub.exception.member.MemberNotFoundException;
import dmu.dmuclub.exception.sign.EmailAlreadyExistsException;
import dmu.dmuclub.exception.sign.LoginFailureException;
import dmu.dmuclub.exception.sign.NicknameAlreadyExistsException;
import dmu.dmuclub.exception.sign.PhoneAlreadyExistsException;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SignService {

    private final MemberDao memberDao;

    public SignService() {
        memberDao = new MemberDao();
    }

    public Response signUp(SignUpResquest signUpRequest) {
        try {
            existsValidate(signUpRequest.getEmail(), signUpRequest.getNickname(), signUpRequest.getPhone());

            memberDao.save(signUpRequest);
            return Response.successResponse();
        } catch (EmailAlreadyExistsException | NicknameAlreadyExistsException | PhoneAlreadyExistsException e){
            return new Response(e.getMessage(), "409");
        } catch (SQLException | ClassNotFoundException e) {
            return new Response("회원 가입 중 오류가 발생하였습니다.", "500");
        }
    }

    public Response signIn(SignInRequest signInRequest, HttpSession session) throws SQLException {
        try {
            MemberDto memberDto = memberDao.findByEmail(signInRequest.getEmail());
            if (memberDto == null)
                throw new MemberNotFoundException("가입한 회원정보가 없습니다");

            if (!memberDto.getPassword().equals(signInRequest.getPassword()) )
                throw new LoginFailureException("비밀번호를 확인해 주세요");

            session.setAttribute("member", memberDto);
            return Response.successResponse();
        } catch (LoginFailureException | MemberNotFoundException e) {
            return new Response(e.getMessage(), "400");
        }
    }

    private void existsValidate(String email, String nickname, String phone) throws SQLException, ClassNotFoundException {
        if (memberDao.existsByEmail(email))
            throw new EmailAlreadyExistsException("이메일이 이미 사용중입니다");
        if (memberDao.existsByNickname(nickname))
            throw new NicknameAlreadyExistsException("닉네임이 이미 사용중입니다.");
        if (memberDao.existsByPhone(phone))
            throw new NicknameAlreadyExistsException("핸드폰 번호가 이미 사용중입니다.");
    }
}
